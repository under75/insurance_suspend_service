package ru.tfoms.insurancesuspend.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.insurancesuspend.entity.InsuranceSuspendRequestPoll;

public interface InsuranceSuspendRequestPollRepository extends JpaRepository<InsuranceSuspendRequestPoll, Long> {

	Collection<InsuranceSuspendRequestPoll> findByDtreqIsNull();
	
	Collection<InsuranceSuspendRequestPoll> findByStatusIsNullOrStatusNotIn(Collection<String> ignoredStatuses);   

}
