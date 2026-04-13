package com.neocare.api.infrastructure.persistance;

import com.neocare.api.domain.logging.Logger;
import com.neocare.api.domain.model.MetricaEstresse;
import com.neocare.api.domain.repository.MetricaEstresseRepository;
import com.neocare.api.infrastructure.entity.JpaMedicaoEstresseEntity;
import com.neocare.api.infrastructure.entity.JpaMetricaEstresseEntity;
import com.neocare.api.infrastructure.exception.InfraestruturaException;
import com.neocare.api.infrastructure.repository.JpaMedicaoEstresseRepository;
import com.neocare.api.infrastructure.repository.JpaMetricaEstresseRepository;
import com.neocare.api.interfaces.mapper.MetricaEstresseMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MetricaEstresseRepositoryAdapter implements MetricaEstresseRepository {

    private final JpaMetricaEstresseRepository jpaMetricaEstresseRepository;
    private final JpaMedicaoEstresseRepository jpaMedicaoEstresseRepository;
    private final Logger logger;

    public MetricaEstresseRepositoryAdapter(JpaMetricaEstresseRepository jpaMetricaEstresseRepository,
                                            JpaMedicaoEstresseRepository jpaMedicaoEstresseRepository,
                                            Logger logger) {
        this.jpaMetricaEstresseRepository = jpaMetricaEstresseRepository;
        this.jpaMedicaoEstresseRepository = jpaMedicaoEstresseRepository;
        this.logger = logger;
    }

    @Override
    public MetricaEstresse save(MetricaEstresse metricaEstresse) {
        logger.info("Salvando métrica de estresse para medição ID: " + metricaEstresse.getMedicaoEstresseId());
        JpaMedicaoEstresseEntity medicaoEntity = jpaMedicaoEstresseRepository
                .findById(metricaEstresse.getMedicaoEstresseId())
                .orElseThrow(() -> new InfraestruturaException(
                        "Medição de estresse com ID " + metricaEstresse.getMedicaoEstresseId() + " não encontrada."));

        JpaMetricaEstresseEntity entity = MetricaEstresseMapper.toEntity(metricaEstresse, medicaoEntity);

        try {
            JpaMetricaEstresseEntity savedEntity = jpaMetricaEstresseRepository.save(entity);
            logger.info("Métrica de estresse salva com sucesso. ID: " + savedEntity.getId());
            return MetricaEstresseMapper.toDomain(savedEntity);
        } catch (Exception e) {
            logger.error("Erro ao salvar métrica de estresse: " + e.getMessage(), e);
            throw new InfraestruturaException("Não foi possível salvar a métrica de estresse.", e);
        }
    }

    @Override
    public Optional<MetricaEstresse> findByMedicaoEstresseId(Long medicaoEstresseId) {
        return jpaMetricaEstresseRepository.findByMedicaoEstresseEntityId(medicaoEstresseId)
                .map(MetricaEstresseMapper::toDomain);
    }

    @Override
    public List<MetricaEstresse> findByUsuarioId(Long usuarioId) {
        return jpaMetricaEstresseRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(MetricaEstresseMapper::toDomain)
                .collect(Collectors.toList());
    }
}
