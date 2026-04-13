package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaDispositivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaDispositivoRepository extends JpaRepository<JpaDispositivoEntity, Long> {
    List<JpaDispositivoEntity> findByUsuarioEntityId(Long usuarioId);
}
