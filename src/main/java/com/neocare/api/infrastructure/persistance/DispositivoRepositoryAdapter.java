package com.neocare.api.infrastructure.persistance;

import com.neocare.api.domain.logging.Logger;
import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.domain.repository.DispositivoRepository;
import com.neocare.api.infrastructure.entity.JpaDispositivoEntity;
import com.neocare.api.infrastructure.exception.InfraestruturaException;
import com.neocare.api.infrastructure.repository.JpaDispositivoRepository;

public class DispositivoRepositoryAdapter implements DispositivoRepository {

    private final JpaDispositivoRepository jpaDispositivoRepository;
    private final Logger logger;

    public DispositivoRepositoryAdapter(JpaDispositivoRepository jpaDispositivoRepository, Logger logger) {
        this.jpaDispositivoRepository = jpaDispositivoRepository;
        this.logger = logger;
    }

    @Override
    public Dispositivo findById(Long id) {
        logger.info("Buscando dispositivo por id: " + id);
        try{
            JpaDispositivoEntity entity = jpaDispositivoRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado"));
            logger.info("Dispositivo encontrado: " + entity.getTipoDispositivo() + " endereco dispositivo: " + entity.getEnderecoDisp());
            return DispositivoMapper.jpaEntityToDomain(entity);
        } catch (Exception e) {
            logger.error("Erro ao buscar dispositivo: " + e.getMessage(), e);
            throw new InfraestruturaException("Erro ao buscar dispositivo por id: " + e.getMessage(), e);
        }
    }
}
