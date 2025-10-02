package com.neocare.api.infrastructure.persistance;

import com.neocare.api.domain.logging.Logger;
import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.infrastructure.entity.JpaUsuarioEntity;
import com.neocare.api.infrastructure.exception.InfraestruturaException;
import com.neocare.api.infrastructure.repository.JpaUsuarioRepository;
import com.neocare.api.interfaces.mapper.UsuarioMapper;
import org.springframework.dao.DataAccessException;


public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final Logger logger;

    public UsuarioRepositoryAdapter(JpaUsuarioRepository jpaUsuarioRepository, Logger logger) {
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.logger = logger;
    }


    @Override
    public Usuario save(Usuario usuario) {
        logger.info("Salvando usuário com CPF: " + usuario.getCpf());
        JpaUsuarioEntity entity = UsuarioMapper.toJpa(usuario);

        try{
            JpaUsuarioEntity savedEntity = jpaUsuarioRepository.save(entity);

            logger.info("Usuario salvo com sucesso: " + usuario.getCpf());
            return UsuarioMapper.entityToDomain(savedEntity);
        } catch (DataAccessException e){
            logger.error("Erro ao salvar cliente: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao salvar o cliente " + e.getMessage(), e);
        }
    }
}
