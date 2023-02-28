package ru.tfoms.insurancesuspend.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
import ru.tfoms.insurancesuspend.entity.MPIError;
import ru.tfoms.insurancesuspend.entity.MPIReq;
import ru.tfoms.insurancesuspend.repository.InsuranceSuspendPersonRepository;
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
public class InsuranceSuspendService {

	private final InsuranceSuspendRequestRepository requestRepository;
	private final InsuranceSuspendPersonRepository personRepository;
	private final MPIErrorRepository errorRepository;
	private final MPIReqRepository mpiReqRepository;
	private final WebServiceTemplate template;

	public final static Integer SOAP_ERR = 1;
	public final static Integer LOGIC_ERR = 2;
	public final static String INTERNAL_SERVICE_ERROR = "500";

	@Autowired
	public InsuranceSuspendService(InsuranceSuspendRequestRepository requestRepository,
			InsuranceSuspendPersonRepository personRepository, WebServiceTemplate template,
			MPIErrorRepository errorRepository, MPIReqRepository mpiReqRepository) {
		super();
		this.requestRepository = requestRepository;
		this.personRepository = personRepository;
		this.errorRepository = errorRepository;
		this.mpiReqRepository = mpiReqRepository;
		this.template = template;
	}

//	@Scheduled(cron = "0 * * * * *")
	@Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
	public void prosess() {
		Collection<InsuranceSuspendRequest> requests = requestRepository.findByDtreqIsNull();
		requests.forEach(t -> {
			String extRid = UUID.randomUUID().toString();
			SuspendOmsPolicyStartRequest request = new SuspendOmsPolicyStartRequest();
			request.setPersons(getListOfMilSvcPerson(t));
			request.setExternalRequestId(extRid);

			Marshaller marshaller = template.getMarshaller();
			Unmarshaller unmarshaller = template.getUnmarshaller();
			try {
				SuspendOmsPolicyStartResponse responce = (SuspendOmsPolicyStartResponse) template
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
				
				save(t, responce, extRid);
				
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
	private void save(InsuranceSuspendRequest t, SuspendOmsPolicyStartResponse responce, String extRid) {
		if (responce.getErrors() != null && responce.getErrors().getErrorItem().size() == 1 && responce.getErrors()
				.getErrorItem().stream().findAny().get().getCode().trim().equals(INTERNAL_SERVICE_ERROR)) {
			// do nothing
		} else {
			t.setDtreq(LocalDateTime.now());
			t.setHasError(responce.getErrors() != null ? true : false);
			t.setOpToken(responce.getOpToken());

			requestRepository.save(t);
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
				errEntity.setExtrid(extRid);
				errEntity.setType(LOGIC_ERR);

				errorRepository.save(errEntity);
			}
		}
	}

}