package ru.tfoms.insurancesuspend.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceMessageExtractor;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.support.MarshallingUtils;

import ru.tfoms.insurancesuspend.entity.InsuranceSuspendPerson;
import ru.tfoms.insurancesuspend.entity.InsuranceSuspendRequest;
import ru.tfoms.insurancesuspend.entity.InsuranceSuspendRequestPoll;
import ru.tfoms.insurancesuspend.entity.MPIError;
import ru.tfoms.insurancesuspend.entity.MPIReq;
import ru.tfoms.insurancesuspend.repository.InsuranceSuspendPersonRepository;
import ru.tfoms.insurancesuspend.repository.InsuranceSuspendRequestPollRepository;
import ru.tfoms.insurancesuspend.repository.InsuranceSuspendRequestRepository;
import ru.tfoms.insurancesuspend.repository.MPIErrorRepository;
import ru.tfoms.insurancesuspend.repository.MPIReqRepository;
import ru.tfoms.schemas.person.ListOfMilSvcPerson;
import ru.tfoms.schemas.person.MilSvcData;
import ru.tfoms.schemas.person.MilSvcPerson;
import ru.tfoms.schemas.person.PersonDocIdentSerNum;
import ru.tfoms.schemas.person.PersonFioDr;
import ru.tfoms.schemas.person.ResponseErrorData;
import ru.tfoms.schemas.person.SuspendOmsPolicyStartRequest;
import ru.tfoms.schemas.person.SuspendOmsPolicyStartResponse;

@Component
public class SuspendOmsPolicyStartService implements InsuranceSuspendService {

	private final InsuranceSuspendRequestRepository requestRepository;
	private final InsuranceSuspendRequestPollRepository requestPollRepository;
	private final MPIErrorRepository errorRepository;
	private final MPIReqRepository mpiReqRepository;
	private final WebServiceTemplate template;

	@Autowired
	public SuspendOmsPolicyStartService(InsuranceSuspendRequestRepository requestRepository,
			InsuranceSuspendPersonRepository personRepository, WebServiceTemplate template,
			MPIErrorRepository errorRepository, MPIReqRepository mpiReqRepository, InsuranceSuspendRequestPollRepository requestPollRepository) {
		super();
		this.requestRepository = requestRepository;
		this.requestPollRepository = requestPollRepository;
		this.errorRepository = errorRepository;
		this.mpiReqRepository = mpiReqRepository;
		this.template = template;
	}

	@Scheduled(cron = "0 0/15 8-23 * * *")
	public void process() {
		Collection<InsuranceSuspendRequest> requests = requestRepository.findByDtreqIsNull();
		Marshaller marshaller = template.getMarshaller();
		Unmarshaller unmarshaller = template.getUnmarshaller();
		requests.forEach(t -> {
			String extRid = UUID.randomUUID().toString();
			SuspendOmsPolicyStartRequest request = new SuspendOmsPolicyStartRequest();
			request.setPersons(getListOfMilSvcPerson(t));
			request.setExternalRequestId(extRid);
			try {
				SuspendOmsPolicyStartResponse response = (SuspendOmsPolicyStartResponse) template
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
								mpiReq.setExtrid(extRid);

								mpiReqRepository.save(mpiReq);
							}
						}, new WebServiceMessageExtractor<SuspendOmsPolicyStartResponse>() {

							@Override
							public SuspendOmsPolicyStartResponse extractData(WebServiceMessage message)
									throws IOException, TransformerException {
								ByteArrayOutputStream out = new ByteArrayOutputStream();
								message.writeTo(out);
								MPIReq mpiReq = mpiReqRepository.getByExtrid(extRid);
								mpiReq.setResp(out.toByteArray());

								mpiReqRepository.save(mpiReq);

								return (SuspendOmsPolicyStartResponse) MarshallingUtils.unmarshal(unmarshaller,
										message);
							}
						});
				
				save(t, response, extRid);
				
			} catch (SoapFaultClientException e) {
				t.setDtreq(LocalDateTime.now());
				t.setHasError(true);

				requestRepository.save(t);
				
				MPIError errEntity = new MPIError();
				errEntity.setRid(t.getRid());
				errEntity.setNr(1);
				errEntity.setCode(e.getClass().getSimpleName());
				errEntity.setMessage(e.getFaultStringOrReason());
				errEntity.setDtIns(LocalDateTime.now());
				errEntity.setExtrid(extRid);
				errEntity.setType(SOAP_ERR);

				errorRepository.save(errEntity);
			}

		});
	}

	private ListOfMilSvcPerson getListOfMilSvcPerson(InsuranceSuspendRequest t) {
		ListOfMilSvcPerson listMilSvcPerson = new ListOfMilSvcPerson();
		List<MilSvcPerson> list = listMilSvcPerson.getPerson();

		Collection<InsuranceSuspendPerson> persons = t.getPersons();
		persons.forEach(p -> {
			try {
				MilSvcPerson person = new MilSvcPerson();
				person.setOip(p.getOip());
				person.setSnils(p.getSnils());
				person.setLocalPersonIndex(String.valueOf(p.getNr()));

				PersonFioDr fioDr = new PersonFioDr();
				fioDr.setBirthDay(DatatypeFactory.newInstance().newXMLGregorianCalendar(p.getBirthDay().toString()));
				fioDr.setSurname(p.getLastName());
				fioDr.setFirstName(p.getFirstName());
				fioDr.setPatronymic(p.getPatronymic());
				person.setPersonInfo(fioDr);

				PersonDocIdentSerNum serNum = new PersonDocIdentSerNum();
				serNum.setDudlType(String.valueOf(p.getDudlType()));
				serNum.setDudlSer(p.getDudlSer());
				serNum.setDudlNum(p.getDudlNum());
				person.setDudl(serNum);

				MilSvcData svcData = new MilSvcData();
				svcData.setStartDate(
						DatatypeFactory.newInstance().newXMLGregorianCalendar(p.getEffectiveDate().toString()));
				svcData.setEndDate(
						DatatypeFactory.newInstance().newXMLGregorianCalendar(p.getExpirationDate().toString()));
				svcData.setTermInMonths(p.getTerminmonths());
				person.setMilSvc(svcData);

				list.add(person);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return listMilSvcPerson;
	}

	@Transactional
	private void save(InsuranceSuspendRequest t, SuspendOmsPolicyStartResponse response, String extRid) {
		if (response.getErrors() != null && response.getErrors().getErrorItem().size() == 1 && response.getErrors()
				.getErrorItem().stream().findAny().get().getCode().trim().equals(INTERNAL_SERVICE_ERROR)) {
			// do nothing
		} else {
			t.setDtreq(LocalDateTime.now());
			t.setHasError(response.getErrors() != null ? true : false);
			t.setOpToken(response.getOpToken());

			requestRepository.save(t);
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
				errEntity.setExtrid(extRid);
				errEntity.setType(LOGIC_ERR);

				errorRepository.save(errEntity);
			}
		} else {
			InsuranceSuspendRequestPoll requestPoll = new InsuranceSuspendRequestPoll();
			requestPoll.setParentRid(t.getRid());
			requestPoll.setDtIns(LocalDateTime.now());
			requestPoll.setOpToken(response.getOpToken());
			
			requestPollRepository.save(requestPoll);
		}
	}

}
