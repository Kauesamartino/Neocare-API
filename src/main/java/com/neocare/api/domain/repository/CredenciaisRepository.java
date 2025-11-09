package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.Credenciais;

import java.util.Optional;

public interface CredenciaisRepository {

    Optional<Credenciais> findByUsername(String username);


    Optional<Credenciais> findByUsernameWithRoles(String username);

    boolean existsByUsername(String username);

}
