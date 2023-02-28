package ru.tfoms.insurancesuspend.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MPIReqId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long rid;

	private LocalDateTime dt;
	
	public MPIReqId() {
	}

	public MPIReqId(Long rid, LocalDateTime dt) {
		super();
		this.rid = rid;
		this.dt = dt;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}
	
}
