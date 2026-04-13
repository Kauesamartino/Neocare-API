package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.MetricaEstresse;

import java.util.List;
import java.util.Optional;

public interface MetricaEstresseRepository {

    MetricaEstresse save(MetricaEstresse metricaEstresse);

    Optional<MetricaEstresse> findByMedicaoEstresseId(Long medicaoEstresseId);

    List<MetricaEstresse> findByUsuarioId(Long usuarioId);
}
