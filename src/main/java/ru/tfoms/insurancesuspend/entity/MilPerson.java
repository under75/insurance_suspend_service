package ru.tfoms.insurancesuspend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "MPI_SUSPEND_OMS_POL_POLL_RES", schema = "OMCOWNER")
@IdClass(MilPersonId.class)
public class MilPerson {
	@Id
	@Column(name = "rid")
	private Long rid;
	@Id
	@Column(name = "person_id")
	private String personId;
	
	@Column(name = "oip")
	private String oip;
	
	@Column(name = "err")
	private Boolean err;

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

	public String getOip() {
		return oip;
	}

	public void setOip(String oip) {
		this.oip = oip;
	}

	public Boolean getErr() {
		return err;
	}

	public void setErr(Boolean err) {
		this.err = err;
	}
	
}
