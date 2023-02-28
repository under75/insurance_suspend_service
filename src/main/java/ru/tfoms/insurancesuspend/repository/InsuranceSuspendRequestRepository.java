package ru.tfoms.insurancesuspend.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.insurancesuspend.entity.InsuranceSuspendRequest;

public interface InsuranceSuspendRequestRepository extends JpaRepository<InsuranceSuspendRequest, Long> {

	Collection<InsuranceSuspendRequest> findByDtreqIsNull();
}
