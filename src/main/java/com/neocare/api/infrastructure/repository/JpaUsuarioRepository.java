package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaUsuarioRepository extends JpaRepository<JpaUsuarioEntity, Long> {

    Optional<JpaUsuarioEntity> findByCpf(String cpf);

    List<JpaUsuarioEntity> findAllByAtivoTrue();
}
