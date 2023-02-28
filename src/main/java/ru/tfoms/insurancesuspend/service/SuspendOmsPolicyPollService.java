package ru.tfoms.insurancesuspend.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import ru.tfoms.insurancesuspend.repository.InsuranceSuspendRequestPollRepository;
import ru.tfoms.insurancesuspend.repository.MPIErrorRepository;
import ru.tfoms.insurancesuspend.repository.MPIReqRepository;
import ru.tfoms.schemas.person.MilPersonResult;
import ru.tfoms.schemas.person.ResponseErrorData;
import ru.tfoms.schemas.person.SuspendOmsPolicyPollRequest;
import ru.tfoms.schemas.person.SuspendOmsPolicyPollResponse;

@Component
public class SuspendOmsPolicyPollService implements InsuranceSuspendService {
	private final InsuranceSuspendRequestPollRepository requestPollRepository;
	private final MPIReqRepository mpiReqRepository;
	private final MPIErrorRepository errorRepository;
	private final WebServiceTemplate template;
	
	protected enum Status {
		ERROR,COMPLETED,NEW,PROCESSING
	}

	@Autowired
	public SuspendOmsPolicyPollService(InsuranceSuspendRequestPollRepository requestPollRepository,
			WebServiceTemplate template, MPIReqRepository mpiReqRepository, MPIErrorRepository errorRepository) {
		super();
		this.requestPollRepository = requestPollRepository;
		this.mpiReqRepository = mpiReqRepository;
		this.errorRepository = errorRepository;
		this.template = template;
	}

//	@Scheduled(cron = "0 * * * * *")
	@Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
	public void process() {
		Collection<InsuranceSuspendRequestPoll> requests = requestPollRepository.findByDtreqIsNull();//to do
		requests.forEach(t -> {
			SuspendOmsPolicyPollRequest request = new SuspendOmsPolicyPollRequest();
			request.setExternalRequestId(t.getExtrid());
			request.setOpToken(t.getOpToken());

			Marshaller marshaller = template.getMarshaller();
			Unmarshaller unmarshaller = template.getUnmarshaller();
			try {
				SuspendOmsPolicyPollResponse responce = (SuspendOmsPolicyPollResponse) template
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
								mpiReq.setExtrid(t.getExtrid());

								mpiReqRepository.save(mpiReq);
							}
						}, new WebServiceMessageExtractor<SuspendOmsPolicyPollResponse>() {

							@Override
							public SuspendOmsPolicyPollResponse extractData(WebServiceMessage message)
									throws IOException, TransformerException {
								ByteArrayOutputStream out = new ByteArrayOutputStream();
								message.writeTo(out);
								MPIReq mpiReq = mpiReqRepository.getByExtrid(t.getExtrid());
								mpiReq.setResp(out.toByteArray());

								mpiReqRepository.save(mpiReq);

								return (SuspendOmsPolicyPollResponse) MarshallingUtils.unmarshal(unmarshaller, message);
							}
						});

				save(t, responce);

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
				errEntity.setExtrid(t.getExtrid());
				errEntity.setType(SOAP_ERR);

				errorRepository.save(errEntity);
			}
		});
	}

	private void save(InsuranceSuspendRequestPoll t, SuspendOmsPolicyPollResponse responce) {
		if (responce.getErrors() != null && responce.getErrors().getErrorItem().size() == 1 && responce.getErrors()
				.getErrorItem().stream().findAny().get().getCode().trim().equals(INTERNAL_SERVICE_ERROR)) {
			// do nothing
		} else {
			t.setDtreq(LocalDateTime.now());
			t.setHasError(responce.getErrors() != null ? true : false);
			t.setStatus(responce.getProcessingStatus());

			requestPollRepository.save(t);
		}

		if (responce.getErrors() != null) {
			Collection<ResponseErrorData> errors = responce.getErrors().getErrorItem();
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
				errEntity.setExtrid(t.getExtrid());
				errEntity.setType(LOGIC_ERR);

				errorRepository.save(errEntity);
			}
		} else if (Status.valueOf(responce.getProcessingStatus().toUpperCase().trim()) == Status.COMPLETED){
			List<MilPersonResult> milPersonResult = responce.getMilPersonResult();
		}

	}

}
