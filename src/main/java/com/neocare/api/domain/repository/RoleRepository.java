package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
