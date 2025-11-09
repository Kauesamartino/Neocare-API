package com.neocare.api.infrastructure.persistance;

import com.neocare.api.application.exception.EntidadeNaoEncontradaException;
import com.neocare.api.domain.logging.Logger;
import com.neocare.api.domain.model.Role;
import com.neocare.api.domain.repository.RoleRepository;
import com.neocare.api.infrastructure.entity.JpaRoleEntity;
import com.neocare.api.infrastructure.repository.JpaRoleRepository;
import com.neocare.api.interfaces.mapper.RoleMapper;

import java.util.Optional;

public class RoleRepositoryAdapter implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;
    private final Logger logger;

    public RoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository, Logger logger) {
        this.jpaRoleRepository = jpaRoleRepository;
        this.logger = logger;
    }

    @Override
    public Optional<Role> findByName(String name) {
        logger.info("Buscando role com o nome " + name);

        try{
            JpaRoleEntity entity = jpaRoleRepository.findByName(name)
                    .orElseThrow(() -> new IllegalArgumentException("Role não encontrada: " + name));
            return RoleMapper.toDomain(entity);
        } catch (IllegalArgumentException e){
            logger.error("Erro ao buscar role: " + e.getMessage(), e);
            throw new EntidadeNaoEncontradaException(e.getMessage());
        }
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
