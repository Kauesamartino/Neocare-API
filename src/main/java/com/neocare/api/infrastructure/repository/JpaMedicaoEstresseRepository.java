package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaMedicaoEstresseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMedicaoEstresseRepository extends JpaRepository<JpaMedicaoEstresseEntity, String> {
}
