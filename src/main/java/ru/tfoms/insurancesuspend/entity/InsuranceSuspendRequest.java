package ru.tfoms.insurancesuspend.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "MPI_SUSPEND_OMS_POL_START", schema = "OMCOWNER")
public class InsuranceSuspendRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "rid")
	private Long rid;
	
	@Column(name = "dt_ins")
	private LocalDateTime dtIns;
	
	@Column(name = "usr")
	private String usr;
	
	@Column(name = "dt_req")
	private LocalDateTime dtreq;
	
	@Column(name = "err")
	private Boolean hasError;
	
	@Column(name = "optoken")
	private String opToken;
	
	@OneToMany(fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE) 
	@JoinColumn(name = "rid", referencedColumnName = "rid", insertable=false, updatable=false)
	private Collection<InsuranceSuspendPerson> persons = new ArrayList<>();

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public LocalDateTime getDtIns() {
		return dtIns;
	}

	public void setDtIns(LocalDateTime dtIns) {
		this.dtIns = dtIns;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public LocalDateTime getDtreq() {
		return dtreq;
	}

	public void setDtreq(LocalDateTime dtReq) {
		this.dtreq = dtReq;
	}

	public Boolean getHasError() {
		return hasError;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	public String getOpToken() {
		return opToken;
	}

	public void setOpToken(String opToken) {
		this.opToken = opToken;
	}

	public Collection<InsuranceSuspendPerson> getPersons() {
		return persons;
	}

	public void setPersons(Collection<InsuranceSuspendPerson> persons) {
		this.persons = persons;
	}
	
}
