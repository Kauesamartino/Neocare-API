package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaAlertaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaAlertaRepository extends JpaRepository<JpaAlertaEntity, Long> {
    List<JpaAlertaEntity> findByUsuarioEntityIdOrderByDataNotificacaoDesc(Long usuarioId);

    List<JpaAlertaEntity> findAllByOrderByDataNotificacaoDesc();
}
