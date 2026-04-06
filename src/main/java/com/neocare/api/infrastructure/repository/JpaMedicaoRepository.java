package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaMedicaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMedicaoRepository extends JpaRepository<JpaMedicaoEntity, Long> {
}
