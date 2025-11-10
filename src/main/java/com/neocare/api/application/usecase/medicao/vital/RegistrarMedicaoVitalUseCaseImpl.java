package com.neocare.api.application.usecase.medicao.vital;

import com.neocare.api.domain.model.MedicaoVital;
import com.neocare.api.domain.repository.MedicaoVitalRepository;

public final class RegistrarMedicaoVitalUseCaseImpl implements  RegistrarMedicaoVitalUseCase {

    private final MedicaoVitalRepository medicaoVitalRepository;

    public RegistrarMedicaoVitalUseCaseImpl(MedicaoVitalRepository medicaoVitalRepository) {
        this.medicaoVitalRepository = medicaoVitalRepository;
    }

    @Override
    public MedicaoVital execute(MedicaoVital medicaoVital) {
        return medicaoVitalRepository.save(medicaoVital);
    }
}
