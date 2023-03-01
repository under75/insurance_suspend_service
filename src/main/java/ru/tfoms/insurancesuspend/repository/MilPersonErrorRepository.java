package ru.tfoms.insurancesuspend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.tfoms.insurancesuspend.entity.MilPersonErrId;
import ru.tfoms.insurancesuspend.entity.MilPersonError;

public interface MilPersonErrorRepository extends JpaRepository<MilPersonError, MilPersonErrId>{

}
