package ru.tfoms.insurancesuspend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.insurancesuspend.entity.InsuranceSuspendPersonId;
import ru.tfoms.insurancesuspend.entity.MPIError;

public interface MPIErrorRepository extends JpaRepository<MPIError, InsuranceSuspendPersonId> {

}
