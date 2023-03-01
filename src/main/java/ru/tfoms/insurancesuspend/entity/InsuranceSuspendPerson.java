package ru.tfoms.insurancesuspend.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "MPI_SUSPEND_OMS_POL_DATA", schema = "OMCOWNER")
@IdClass(InsuranceSuspendPersonId.class)
public class InsuranceSuspendPerson implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "rid")
	private Long rid;

	@Id
	@Column(name = "nr")
	private Integer nr;
	
	@Column(name = "oip")
	private String oip;
	
	@Column(name = "im")
	private String firstName;

	@Column(name = "fam")
	private String lastName;

	@Column(name = "ot")
	private String patronymic;
	
	@Column(name = "dr")
	private LocalDate birthDay;
	
	@Column(name = "dudltype")
	private Integer dudlType;
	
	@Column(name = "dudlser")
	private String dudlSer;

	@Column(name = "dudlnum")
	private String dudlNum;
	
	@Column(name = "snils")
	private String snils;
	
	@Column(name = "startdate")
	private LocalDate effectiveDate;
	
	@Column(name = "enddate")
	private LocalDate expirationDate;
	
	@Column(name = "terminmonths")
	private Integer terminmonths;
	
	@Column(name = "id_pol")
	private Long policyId;

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}

	public String getOip() {
		return oip;
	}

	public void setOip(String oip) {
		this.oip = oip;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getDudlType() {
		return dudlType;
	}

	public void setDudlType(Integer dudlType) {
		this.dudlType = dudlType;
	}

	public String getDudlSer() {
		return dudlSer;
	}

	public void setDudlSer(String dudlSer) {
		this.dudlSer = dudlSer;
	}

	public String getDudlNum() {
		return dudlNum;
	}

	public void setDudlNum(String dudlNum) {
		this.dudlNum = dudlNum;
	}

	public String getSnils() {
		return snils;
	}

	public void setSnils(String snils) {
		this.snils = snils;
	}

	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getTerminmonths() {
		return terminmonths;
	}

	public void setTerminmonths(Integer terminmonths) {
		this.terminmonths = terminmonths;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	
}
