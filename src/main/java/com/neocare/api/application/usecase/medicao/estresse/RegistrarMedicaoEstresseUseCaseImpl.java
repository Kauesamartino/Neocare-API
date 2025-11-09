package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;

public final class RegistrarMedicaoEstresseUseCaseImpl implements RegistrarMedicaoEstresseUseCase{

    private final MedicaoEstresseRepository medicaoEstresseRepository;

    public RegistrarMedicaoEstresseUseCaseImpl(MedicaoEstresseRepository medicaoEstresseRepository) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
    }

    @Override
    public MedicaoEstresse execute(MedicaoEstresse medicaoEstresse) {
        return medicaoEstresseRepository.save(medicaoEstresse);
    }
}
