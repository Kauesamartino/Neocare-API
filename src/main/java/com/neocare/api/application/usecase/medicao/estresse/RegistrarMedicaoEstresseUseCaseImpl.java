package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.model.Alerta;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.model.MetricaEstresse;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;
import com.neocare.api.domain.repository.MetricaEstresseRepository;

public final class RegistrarMedicaoEstresseUseCaseImpl implements RegistrarMedicaoEstresseUseCase{

    private final MedicaoEstresseRepository medicaoEstresseRepository;
    private final AlertaRepository alertaRepository;
    private final MetricaEstresseRepository metricaEstresseRepository;

    public RegistrarMedicaoEstresseUseCaseImpl(MedicaoEstresseRepository medicaoEstresseRepository,
                                               AlertaRepository alertaRepository,
                                               MetricaEstresseRepository metricaEstresseRepository) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
        this.alertaRepository = alertaRepository;
        this.metricaEstresseRepository = metricaEstresseRepository;
    }

    @Override
    public MedicaoEstresse execute(MedicaoEstresse medicaoEstresse) {
        MedicaoEstresse salva = medicaoEstresseRepository.save(medicaoEstresse);
        Alerta.avaliarEstresse(salva).ifPresent(alertaRepository::save);

        MetricaEstresse metrica = MetricaEstresse.calcular(
                salva.getId(),
                salva.getVariacaoFrequenciaCardiaca(),
                salva.getCondutividadePele()
        );
        metricaEstresseRepository.save(metrica);

        return salva;
    }
}
