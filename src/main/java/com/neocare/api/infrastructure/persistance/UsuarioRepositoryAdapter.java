package com.neocare.api.infrastructure.persistance;

import com.neocare.api.application.exception.EntidadeNaoEncontradaException;
import com.neocare.api.domain.logging.Logger;
import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.infrastructure.entity.JpaUsuarioEntity;
import com.neocare.api.infrastructure.exception.InfraestruturaException;
import com.neocare.api.infrastructure.repository.JpaUsuarioRepository;
import com.neocare.api.interfaces.mapper.UsuarioMapper;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.stream.Collectors;


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
            logger.error("Erro ao salvar usuario: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao salvar o usuario " + e.getMessage(), e);
        }
    }

    @Override
    public Usuario findByCpf(String cpf) {
        logger.info("Buscando usuario por cpf: " + cpf);
        try {
            JpaUsuarioEntity entity = jpaUsuarioRepository.findByCpf(cpf)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário do id: " + cpf + " não encontrado"));

            logger.info("Usuario encontrado: " + entity.getId() + " " + entity.getSobrenome());
            return UsuarioMapper.entityToDomain(entity);
        } catch (DataAccessException e){
            logger.error("Erro ao buscar usuario: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar usuario por cpf: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> findAllByAtivoTrue() {
        logger.info("Buscando todos os usuarios ativos");
        try{
            List<JpaUsuarioEntity> usuarios = jpaUsuarioRepository.findAllByAtivoTrue();
            logger.info("Usuarios ativos encontrados: " + usuarios.size());
            return usuarios.stream()
                    .map(UsuarioMapper::entityToDomain)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new InfraestruturaException("Erro ao buscar usuarios ativos " + e.getMessage(), e);
        }
    }

    @Override
    public Usuario update(Usuario usuario) {
        logger.info("Atualizando usuário com CPF: " + usuario.getCpf());

        try {
            JpaUsuarioEntity entity = jpaUsuarioRepository.findByCpf(usuario.getCpf())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário do id: " + usuario.getCpf() + " não encontrado"));

            logger.info("Usuario encontrado: " + entity.getId() + " " + entity.getSobrenome());

            entity.setNome(usuario.getNome());
            entity.setSobrenome(usuario.getSobrenome());
            entity.setEmail(usuario.getEmail());
            entity.setTelefone(usuario.getTelefone());
            entity.setDataNascimento(usuario.getDataNascimento());
            entity.setSexo(usuario.getSexo());
            entity.setAltura(usuario.getAltura());
            entity.setPeso(usuario.getPeso());
            entity.setEndereco(UsuarioMapper.toEnderecoModel(
                    usuario.getEndereco().getLogradouro(),
                    usuario.getEndereco().getBairro(),
                    usuario.getEndereco().getCep(),
                    usuario.getEndereco().getNumero(),
                    usuario.getEndereco().getComplemento(),
                    usuario.getEndereco().getCidade(),
                    usuario.getEndereco().getUf()
            ));

            JpaUsuarioEntity updatedEntity = jpaUsuarioRepository.save(entity);
            logger.info("Usuário atualizado com sucesso: " + usuario.getCpf());

            return UsuarioMapper.entityToDomain(updatedEntity);
        } catch (DataAccessException e){
            logger.error("Erro ao buscar usuario: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar usuario por cpf: " + e.getMessage(), e);
        }
    }
}
