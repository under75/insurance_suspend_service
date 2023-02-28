package ru.tfoms.insurancesuspend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsuranceSuspendServiceClientApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(InsuranceSuspendServiceClientApplication.class, args);
	}
/**
	@Bean
	CommandLineRunner lookup(WebServiceTemplate template) {
		return args -> {
			SuspendOmsPolicyStartRequest request = new SuspendOmsPolicyStartRequest();

			MilSvcPerson person = new MilSvcPerson();
			person.setOip("302219303394");
			person.setSnils("042-153-885 33");
			person.setLocalPersonIndex("1");

			PersonFioDr fioDr = new PersonFioDr();
			fioDr.setBirthDay(
					DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.of(1976, 7, 21).toString()));
			fioDr.setSurname("Каюшкин");
			fioDr.setFirstName("Павел");
			fioDr.setPatronymic("Валерьевич");
			person.setPersonInfo(fioDr);

			PersonDocIdentSerNum serNum = new PersonDocIdentSerNum();
			serNum.setDudlType("14");
			serNum.setDudlSer("63 03");
			serNum.setDudlNum("166033");
			person.setDudl(serNum);

			MilSvcData svcData = new MilSvcData();
			svcData.setStartDate(
					DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.of(1999, 4, 01).toString()));
			svcData.setEndDate(
					DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.of(2000, 7, 20).toString()));
			svcData.setTermInMonths(10);
			person.setMilSvc(svcData);

			ListOfMilSvcPerson listMilSvcPerson = new ListOfMilSvcPerson();
			List<MilSvcPerson> list = listMilSvcPerson.getPerson();
			list.add(person);

			request.setPersons(listMilSvcPerson);
			request.setExternalRequestId("15206");

//			SuspendOmsPolicyStartResponse responce = (SuspendOmsPolicyStartResponse) soapConnector.callWebService("http://10.255.86.30/goznak/hotfix2/api/t-foms/integration/ws/wsdl/insuranceSuspendServiceWs", request);
			try {
				SuspendOmsPolicyStartResponse responce = (SuspendOmsPolicyStartResponse) template
						.marshalSendAndReceive(request);
				System.out.println(responce.getExternalRequestId());
			} catch (SoapFaultClientException e) {
				SoapFaultDetail soapFaultDetail = e.getSoapFault().getFaultDetail();
				SoapFaultDetailElement detailElementChild = (SoapFaultDetailElement) soapFaultDetail.getDetailEntries()
						.next();
				Source detailSource = detailElementChild.getSource();

				try {
					SuspendOmsPolicyStartResponse res = ((JAXBElement<SuspendOmsPolicyStartResponse>) template
							.getUnmarshaller().unmarshal(detailSource)).getValue();
				} catch (IOException e1) {
					throw new IllegalArgumentException(
							"cannot unmarshal SOAP fault detail object: " + soapFaultDetail.getSource());
				}
			}

//			System.out.println(responce.getExternalRequestId());
		};
	}
**/
}
