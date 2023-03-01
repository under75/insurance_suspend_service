package ru.tfoms.insurancesuspend.entity;

import java.io.Serializable;

public class MilPersonErrId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long rid;
	
	private String personId;
	
	private Integer nr;
	
	public MilPersonErrId() {
	}

	public MilPersonErrId(Long rid, String personId, Integer nr) {
		super();
		this.rid = rid;
		this.personId = personId;
		this.nr = nr;
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

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}
	
	
}
