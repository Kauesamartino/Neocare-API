package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaMedicaoVitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMedicaoVitalRepository extends JpaRepository<JpaMedicaoVitalEntity, Long> {
}
