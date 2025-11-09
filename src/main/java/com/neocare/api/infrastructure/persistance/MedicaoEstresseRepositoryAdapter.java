package com.neocare.api.infrastructure.persistance;

import com.neocare.api.domain.logging.Logger;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;
import com.neocare.api.infrastructure.entity.JpaDispositivoEntity;
import com.neocare.api.infrastructure.entity.JpaMedicaoEstresseEntity;
import com.neocare.api.infrastructure.entity.JpaUsuarioEntity;
import com.neocare.api.infrastructure.exception.InfraestruturaException;
import com.neocare.api.infrastructure.repository.JpaDispositivoRepository;
import com.neocare.api.infrastructure.repository.JpaMedicaoEstresseRepository;
import com.neocare.api.infrastructure.repository.JpaUsuarioRepository;
import com.neocare.api.interfaces.mapper.MedicaoEstresseMapper;

public class MedicaoEstresseRepositoryAdapter implements MedicaoEstresseRepository {

    private final JpaMedicaoEstresseRepository jpaMedicaoEstresseRepository;
    private final JpaUsuarioRepository jpaUsuarioRepository;
    private final JpaDispositivoRepository jpaDispositivoRepository;
    private final Logger logger;

    public MedicaoEstresseRepositoryAdapter(JpaMedicaoEstresseRepository jpaMedicaoEstresseRepository, JpaUsuarioRepository jpaUsuarioRepository, JpaDispositivoRepository jpaDispositivoRepository, Logger logger) {
        this.jpaMedicaoEstresseRepository = jpaMedicaoEstresseRepository;
        this.jpaUsuarioRepository = jpaUsuarioRepository;
        this.jpaDispositivoRepository = jpaDispositivoRepository;
        this.logger = logger;
    }

    @Override
    public MedicaoEstresse save(MedicaoEstresse medicaoEstresse) {
        logger.info("Salvando medição de estresse no banco de dados.");
        JpaUsuarioEntity usuarioEntity = jpaUsuarioRepository.findById(medicaoEstresse.getIdUsuario())
                .orElseThrow(() -> new InfraestruturaException("Usuário com ID " + medicaoEstresse.getIdUsuario() + " não encontrado."));
        JpaDispositivoEntity dispositivoEntity = jpaDispositivoRepository.findById(medicaoEstresse.getIdDispositivo())
                .orElseThrow(() -> new InfraestruturaException("Dispositivo com ID " + medicaoEstresse.getIdDispositivo() + " não encontrado."));
        JpaMedicaoEstresseEntity entity = MedicaoEstresseMapper.toEntity(medicaoEstresse, usuarioEntity, dispositivoEntity);

        try {
            logger.info("Iniciando operação de salvamento da medição de estresse.");
            JpaMedicaoEstresseEntity savedEntity = jpaMedicaoEstresseRepository.save(entity);
            logger.info("Medição de estresse salva com sucesso. ID: " + medicaoEstresse.getId());

            return MedicaoEstresseMapper.jpaToDomain(savedEntity);
        } catch (Exception e) {
            logger.error("Erro ao salvar medição de estresse: " + e.getMessage(), e);
            throw new InfraestruturaException("Não foi possível salvar a medição de estresse.", e);
        }

    }
}
