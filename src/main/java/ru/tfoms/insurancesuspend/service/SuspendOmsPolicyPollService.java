package ru.tfoms.insurancesuspend.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.support.MarshallingUtils;

import ru.tfoms.insurancesuspend.entity.InsuranceSuspendRequestPoll;
import ru.tfoms.insurancesuspend.entity.MPIError;
import ru.tfoms.insurancesuspend.entity.MPIReq;
import ru.tfoms.insurancesuspend.entity.MilPerson;
import ru.tfoms.insurancesuspend.entity.MilPersonError;
import ru.tfoms.insurancesuspend.repository.InsuranceSuspendRequestPollRepository;
import ru.tfoms.insurancesuspend.repository.MPIErrorRepository;
import ru.tfoms.insurancesuspend.repository.MPIReqRepository;
import ru.tfoms.insurancesuspend.repository.MilPersonErrorRepository;
import ru.tfoms.insurancesuspend.repository.MilPersonRepository;
import ru.tfoms.schemas.person.MilPersonResult;
import ru.tfoms.schemas.person.ResponseErrorData;
import ru.tfoms.schemas.person.SuspendOmsPolicyPollRequest;
import ru.tfoms.schemas.person.SuspendOmsPolicyPollResponse;

@Component
public class SuspendOmsPolicyPollService implements InsuranceSuspendService {
	private final InsuranceSuspendRequestPollRepository requestPollRepository;
	private final MPIReqRepository mpiReqRepository;
	private final MPIErrorRepository errorRepository;
	private final MilPersonRepository milPersonRepository;
	private final MilPersonErrorRepository milPersonErrorRepository;
	private final WebServiceTemplate template;

	public enum Status {
		ERROR, COMPLETED, NEW, PROCESSING
	}

	public static Collection<String> ignoredStatuses = new ArrayList<>();
	static {
		ignoredStatuses.add(Status.COMPLETED.name());
		ignoredStatuses.add(Status.ERROR.name());
	}

	@Autowired
	public SuspendOmsPolicyPollService(InsuranceSuspendRequestPollRepository requestPollRepository,
			WebServiceTemplate template, MPIReqRepository mpiReqRepository, MPIErrorRepository errorRepository,
			MilPersonRepository milPersonRepository, MilPersonErrorRepository milPersonErrorRepository) {
		super();
		this.requestPollRepository = requestPollRepository;
		this.mpiReqRepository = mpiReqRepository;
		this.errorRepository = errorRepository;
		this.milPersonRepository = milPersonRepository;
		this.milPersonErrorRepository = milPersonErrorRepository;
		this.template = template;
	}

	@Scheduled(cron = "0 0/15 8-23 * * *")
	public void process() {
		Collection<InsuranceSuspendRequestPoll> requests = requestPollRepository.findByStatusIsNullOrStatusNotIn(ignoredStatuses);
		Marshaller marshaller = template.getMarshaller();
		Unmarshaller unmarshaller = template.getUnmarshaller();
		requests.forEach(t -> {
			SuspendOmsPolicyPollRequest request = new SuspendOmsPolicyPollRequest();
			request.setExternalRequestId(UUID.randomUUID().toString());
			request.setOpToken(t.getOpToken());
			try {
				SuspendOmsPolicyPollResponse response = (SuspendOmsPolicyPollResponse) template
						.sendAndReceive(new WebServiceMessageCallback() {

							@Override
							public void doWithMessage(WebServiceMessage message)
									throws IOException, TransformerException {
								MarshallingUtils.marshal(marshaller, request, message);

								ByteArrayOutputStream out = new ByteArrayOutputStream();
								message.writeTo(out);
								MPIReq mpiReq = new MPIReq();
								mpiReq.setRid(t.getRid());
								mpiReq.setDt(LocalDateTime.now());
								mpiReq.setReq(out.toByteArray());
								mpiReq.setExtrid(request.getExternalRequestId());

								mpiReqRepository.save(mpiReq);
							}
						}, new WebServiceMessageExtractor<SuspendOmsPolicyPollResponse>() {

							@Override
							public SuspendOmsPolicyPollResponse extractData(WebServiceMessage message)
									throws IOException, TransformerException {
								ByteArrayOutputStream out = new ByteArrayOutputStream();
								message.writeTo(out);
								MPIReq mpiReq = mpiReqRepository.getByExtrid(request.getExternalRequestId());
								mpiReq.setResp(out.toByteArray());

								mpiReqRepository.save(mpiReq);

								return (SuspendOmsPolicyPollResponse) MarshallingUtils.unmarshal(unmarshaller, message);
							}
						});

				save(t, response);

			} catch (SoapFaultClientException e) {
				t.setDtreq(LocalDateTime.now());
				t.setHasError(true);

				requestPollRepository.save(t);

				MPIError errEntity = new MPIError();
				errEntity.setRid(t.getRid());
				errEntity.setNr(1);
				errEntity.setCode(e.getClass().getSimpleName());
				errEntity.setMessage(e.getFaultStringOrReason());
				errEntity.setDtIns(LocalDateTime.now());
				errEntity.setExtrid(request.getExternalRequestId());
				errEntity.setType(SOAP_ERR);

				errorRepository.save(errEntity);
			}
		});
	}

	private void save(InsuranceSuspendRequestPoll t, SuspendOmsPolicyPollResponse response) {
		if (response.getErrors() != null && response.getErrors().getErrorItem().size() == 1 && response.getErrors()
				.getErrorItem().stream().findAny().get().getCode().trim().equals(INTERNAL_SERVICE_ERROR)) {
			// do nothing
		} else {
			t.setDtreq(LocalDateTime.now());
			t.setHasError(response.getErrors() != null ? true : false);
			t.setStatus(response.getProcessingStatus());
			t.setExtrid(response.getExternalRequestId());

			requestPollRepository.save(t);
		}

		if (response.getErrors() != null) {
			Collection<ResponseErrorData> errors = response.getErrors().getErrorItem();
			int nr = 0;
			for (ResponseErrorData err : errors) {
				if (err.getCode().trim().equals(INTERNAL_SERVICE_ERROR))
					continue; // do nothing
				MPIError errEntity = new MPIError();
				errEntity.setRid(t.getRid());
				errEntity.setNr(++nr);
				errEntity.setCode(err.getCode());
				errEntity.setMessage(err.getMessage());
				errEntity.setTag(err.getTag());
				errEntity.setValue(err.getValue());
				errEntity.setDtIns(LocalDateTime.now());
				errEntity.setExtrid(response.getExternalRequestId());
				errEntity.setType(LOGIC_ERR);

				errorRepository.save(errEntity);
			}
		} else if (Status.valueOf(response.getProcessingStatus().toUpperCase().trim()) == Status.COMPLETED) {
			List<MilPersonResult> milPersonResult = response.getMilPersonResult();
			milPersonResult.forEach(pr -> {
				MilPerson milPers = new MilPerson();
				milPers.setRid(t.getRid());
				milPers.setPersonId(pr.getLocalPersonIndex());
				milPers.setOip(pr.getOip());
				milPers.setErr(pr.getErrors() != null);

				milPersonRepository.save(milPers);
				int nr = 0;
				if (pr.getErrors() != null) {
					Collection<ResponseErrorData> respErrors = pr.getErrors().getErrorItem();
					for (ResponseErrorData respErr : respErrors) {
						MilPersonError error = new MilPersonError();
						error.setRid(t.getRid());
						error.setPersonId(pr.getLocalPersonIndex());
						error.setNr(++nr);
						error.setCode(respErr.getCode());
						error.setMessage(respErr.getMessage());
						error.setTag(respErr.getTag());
						error.setValue(respErr.getValue());

						milPersonErrorRepository.save(error);
					}
				}
			});
		}

	}

}
