package ru.tfoms.insurancesuspend.entity;

import java.io.Serializable;

public class MilPersonId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long rid;
	
	private String personId;
	
	public MilPersonId() {
	}

	public MilPersonId(Long rid, String personId) {
		super();
		this.rid = rid;
		this.personId = personId;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
}
