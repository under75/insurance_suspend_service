package ru.tfoms.insurancesuspend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.insurancesuspend.entity.MPIReq;
import ru.tfoms.insurancesuspend.entity.MPIReqId;

public interface MPIReqRepository extends JpaRepository<MPIReq, MPIReqId> {
	
	MPIReq getByExtrid(String extRid);
}
