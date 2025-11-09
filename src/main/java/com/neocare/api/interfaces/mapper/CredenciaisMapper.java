package com.neocare.api.interfaces.mapper;

import com.neocare.api.domain.model.Credenciais;
import com.neocare.api.domain.model.Role;
import com.neocare.api.infrastructure.entity.JpaCredenciaisEntity;
import com.neocare.api.infrastructure.entity.JpaRoleEntity;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class CredenciaisMapper {
    public static Optional<Credenciais> jpaEntityToDomain(JpaCredenciaisEntity entity) {

        Credenciais credenciais = new Credenciais();

        credenciais.setId(entity.getId());
        credenciais.setUsername(entity.getUsername());
        credenciais.setPassword(entity.getPassword());
        credenciais.setFullName(entity.getFullName());
        credenciais.setCreatedAt(entity.getCreatedAt());
        credenciais.setUpdatedAt(entity.getUpdatedAt());
        Set<Role> roles = Optional.ofNullable(entity.getRoles())
                .orElseGet(Set::of)
                .stream()
                .map(roleEntity -> new Role(
                        roleEntity.getName(),
                        roleEntity.getDescription()
                ))
                .collect(Collectors.toSet());

        credenciais.setRoles(roles);

        return Optional.of(credenciais);
    }

    public static JpaCredenciaisEntity toEntity(Credenciais credenciais, JpaRoleEntity roleEntity) {
                return new JpaCredenciaisEntity(
                credenciais.getUsername(),
                credenciais.getPassword(),
                credenciais.getFullName(),
                Set.of(roleEntity),
                credenciais.getCreatedAt(),
                credenciais.getUpdatedAt()
        );
    }

}
