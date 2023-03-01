package ru.tfoms.insurancesuspend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.insurancesuspend.entity.MilPerson;
import ru.tfoms.insurancesuspend.entity.MilPersonId;

public interface MilPersonRepository extends JpaRepository<MilPerson, MilPersonId>{

}
