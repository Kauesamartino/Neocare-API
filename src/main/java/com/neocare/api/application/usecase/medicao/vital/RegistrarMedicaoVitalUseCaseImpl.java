package com.neocare.api.application.usecase.medicao.vital;

import com.neocare.api.domain.model.Alerta;
import com.neocare.api.domain.model.MedicaoVital;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.domain.repository.MedicaoVitalRepository;

public final class RegistrarMedicaoVitalUseCaseImpl implements  RegistrarMedicaoVitalUseCase {

    private final MedicaoVitalRepository medicaoVitalRepository;
    private final AlertaRepository alertaRepository;

    public RegistrarMedicaoVitalUseCaseImpl(MedicaoVitalRepository medicaoVitalRepository, AlertaRepository alertaRepository) {
        this.medicaoVitalRepository = medicaoVitalRepository;
        this.alertaRepository = alertaRepository;
    }

    @Override
    public MedicaoVital execute(MedicaoVital medicaoVital) {
        MedicaoVital salva = medicaoVitalRepository.save(medicaoVital);
        Alerta.avaliarVital(salva).ifPresent(alertaRepository::save);
        return salva;
    }
}
