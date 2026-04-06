package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.model.Alerta;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;

public final class RegistrarMedicaoEstresseUseCaseImpl implements RegistrarMedicaoEstresseUseCase{

    private final MedicaoEstresseRepository medicaoEstresseRepository;
    private final AlertaRepository alertaRepository;

    public RegistrarMedicaoEstresseUseCaseImpl(MedicaoEstresseRepository medicaoEstresseRepository, AlertaRepository alertaRepository) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
        this.alertaRepository = alertaRepository;
    }

    @Override
    public MedicaoEstresse execute(MedicaoEstresse medicaoEstresse) {
        MedicaoEstresse salva = medicaoEstresseRepository.save(medicaoEstresse);
        Alerta.avaliarEstresse(salva).ifPresent(alertaRepository::save);
        return salva;
    }
}
