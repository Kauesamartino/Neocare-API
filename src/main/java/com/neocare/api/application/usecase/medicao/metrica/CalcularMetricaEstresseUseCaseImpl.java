package com.neocare.api.application.usecase.medicao.metrica;

import com.neocare.api.domain.model.MetricaEstresse;
import com.neocare.api.domain.repository.MetricaEstresseRepository;

public final class CalcularMetricaEstresseUseCaseImpl implements CalcularMetricaEstresseUseCase {

    private final MetricaEstresseRepository metricaEstresseRepository;

    public CalcularMetricaEstresseUseCaseImpl(MetricaEstresseRepository metricaEstresseRepository) {
        this.metricaEstresseRepository = metricaEstresseRepository;
    }

    @Override
    public MetricaEstresse execute(Long medicaoEstresseId, Double hrv, Double gsr) {
        MetricaEstresse metrica = MetricaEstresse.calcular(medicaoEstresseId, hrv, gsr);
        return metricaEstresseRepository.save(metrica);
    }
}
