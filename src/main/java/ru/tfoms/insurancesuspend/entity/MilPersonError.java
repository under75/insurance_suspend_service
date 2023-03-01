package ru.tfoms.insurancesuspend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "MPI_SUSPEND_OMS_POL_POLL_ERR", schema = "OMCOWNER")
@IdClass(MilPersonErrId.class)
public class MilPersonError {
	@Id
	@Column(name = "rid")
	private Long rid;
	@Id
	@Column(name = "person_id")
	private String personId;
	@Id
	@Column(name = "nr")
	private Integer nr;
	
	@Column(name = "cod")
	private String code;

	@Column(name = "message")
	private String message;

	@Column(name = "tag")
	private String tag;

	@Column(name = "val")
	private String value;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
