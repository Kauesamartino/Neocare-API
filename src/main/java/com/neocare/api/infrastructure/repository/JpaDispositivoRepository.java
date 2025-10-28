package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaDispositivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDispositivoRepository extends JpaRepository<JpaDispositivoEntity, Long> {
}
