package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaAlertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAlertaRepository extends JpaRepository<JpaAlertaEntity, Long> {
}
