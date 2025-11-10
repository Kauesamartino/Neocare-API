package com.neocare.api.infrastructure.repository;

import com.neocare.api.infrastructure.entity.JpaCredenciaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface JpaCredenciaisRepository extends JpaRepository<JpaCredenciaisEntity, String> {
    JpaCredenciaisEntity findByUsername(String username);

    @Query("SELECT u FROM JpaCredenciaisEntity u JOIN FETCH u.roles WHERE u.username = :username")
    JpaCredenciaisEntity findByUsernameWithRoles(String username);

    boolean existsByUsername(String username);
}
