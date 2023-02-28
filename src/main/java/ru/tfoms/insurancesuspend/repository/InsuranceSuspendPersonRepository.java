package ru.tfoms.insurancesuspend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.insurancesuspend.entity.InsuranceSuspendPerson;
import ru.tfoms.insurancesuspend.entity.InsuranceSuspendPersonId;

public interface InsuranceSuspendPersonRepository extends JpaRepository<InsuranceSuspendPerson, InsuranceSuspendPersonId>{

}
