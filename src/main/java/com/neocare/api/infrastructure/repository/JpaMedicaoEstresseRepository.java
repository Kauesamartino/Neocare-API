package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaMedicaoEstresseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMedicaoEstresseRepository extends JpaRepository<JpaMedicaoEstresseEntity, Long> {
    List<JpaMedicaoEstresseEntity> findByJpaUsuarioEntityIdOrderByDataMedicaoDesc(Long usuarioId);
}
