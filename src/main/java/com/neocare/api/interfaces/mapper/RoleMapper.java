package com.neocare.api.interfaces.mapper;

import com.neocare.api.domain.model.Role;
import com.neocare.api.infrastructure.entity.JpaRoleEntity;

import java.util.Optional;

public final class RoleMapper {

    public static Optional<Role> toDomain(JpaRoleEntity entity) {
        return Optional.of(new Role(
                entity.getName(),
                entity.getDescription()
        ));
    }
}
