package com.neocare.api.application.usecase.medicao.metrica;

import com.neocare.api.domain.model.MetricaEstresse;

public interface CalcularMetricaEstresseUseCase {
    MetricaEstresse execute(Long medicaoEstresseId, Double hrv, Double gsr);
}
