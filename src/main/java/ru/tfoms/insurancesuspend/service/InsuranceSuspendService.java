package ru.tfoms.insurancesuspend.service;

public interface InsuranceSuspendService {
	public final static Integer SOAP_ERR = 1;
	public final static Integer LOGIC_ERR = 2;
	public final static String INTERNAL_SERVICE_ERROR = "500";
	
	void process();

}
