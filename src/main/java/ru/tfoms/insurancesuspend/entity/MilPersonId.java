package ru.tfoms.insurancesuspend.entity;

import java.io.Serializable;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(personId, rid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MilPersonId other = (MilPersonId) obj;
		return Objects.equals(personId, other.personId) && Objects.equals(rid, other.rid);
	}
	
}
