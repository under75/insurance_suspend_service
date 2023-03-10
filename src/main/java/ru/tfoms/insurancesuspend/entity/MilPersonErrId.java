package ru.tfoms.insurancesuspend.entity;

import java.io.Serializable;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(nr, personId, rid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MilPersonErrId other = (MilPersonErrId) obj;
		return Objects.equals(nr, other.nr) && Objects.equals(personId, other.personId)
				&& Objects.equals(rid, other.rid);
	}
	
}
