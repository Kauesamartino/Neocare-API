package com.neocare.api.infrastructure.persistance;

import com.neocare.api.domain.logging.Logger;
import com.neocare.api.domain.model.Alerta;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.infrastructure.entity.JpaAlertaEntity;
import com.neocare.api.infrastructure.entity.JpaMedicaoEntity;
import com.neocare.api.infrastructure.entity.JpaUsuarioEntity;
import com.neocare.api.infrastructure.exception.InfraestruturaException;
import com.neocare.api.infrastructure.repository.JpaAlertaRepository;
import com.neocare.api.infrastructure.repository.JpaMedicaoRepository;
import com.neocare.api.infrastructure.repository.JpaUsuarioRepository;
import com.neocare.api.interfaces.mapper.AlertaMapper;

public class AlertaRepositoryAdapter implements AlertaRepository {

    private final JpaAlertaRepository jpaAlertaRepository;
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final JpaMedicaoRepository jpaMedicaoRepository;
    private final Logger logger;

    public AlertaRepositoryAdapter(JpaAlertaRepository jpaAlertaRepository, JpaUsuarioRepository jpaUsuarioRepository, JpaMedicaoRepository jpaMedicaoRepository, Logger logger) {
        this.jpaAlertaRepository = jpaAlertaRepository;
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.jpaMedicaoRepository = jpaMedicaoRepository;
        this.logger = logger;
    }

    @Override
    public Alerta save(Alerta alerta) {
        logger.info("Salvando alerta no banco de dados.");
        JpaUsuarioEntity usuarioEntity = jpaUsuarioRepository.findById(alerta.getUsuarioId())
                .orElseThrow(() -> new InfraestruturaException("Usuário com ID " + alerta.getUsuarioId() + " não encontrado."));
        JpaMedicaoEntity medicaoEntity = jpaMedicaoRepository.findById(alerta.getMedicaoId())
                .orElseThrow(() -> new InfraestruturaException("Medição com ID " + alerta.getMedicaoId() + " não encontrada."));
        JpaAlertaEntity entity = AlertaMapper.toEntity(alerta, usuarioEntity, medicaoEntity);

        try {
            JpaAlertaEntity savedEntity = jpaAlertaRepository.save(entity);
            logger.info("Alerta salvo com sucesso. ID: " + savedEntity.getId());
            return AlertaMapper.toDomain(savedEntity);
        } catch (Exception e) {
            logger.error("Erro ao salvar alerta: " + e.getMessage(), e);
            throw new InfraestruturaException("Não foi possível salvar o alerta.", e);
        }
    }
}
