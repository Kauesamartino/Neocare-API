package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaRoleRepository extends JpaRepository<JpaRoleEntity, Long> {
    Optional<JpaRoleEntity> findByName(String name);

    boolean existsByName(String name);
}
