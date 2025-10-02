package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntity, Long> {
}
