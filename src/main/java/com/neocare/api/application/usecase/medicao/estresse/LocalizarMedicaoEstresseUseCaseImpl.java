package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.application.exception.EntidadeNaoEncontradaException;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;

public final class LocalizarMedicaoEstresseUseCaseImpl implements LocalizarMedicaoEstresseUseCase {

    private final MedicaoEstresseRepository medicaoEstresseRepository;

    public LocalizarMedicaoEstresseUseCaseImpl(MedicaoEstresseRepository medicaoEstresseRepository) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
    }

    @Override
    public MedicaoEstresse execute(Long id) {
        return medicaoEstresseRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Medição de estresse com ID " + id + " não encontrada."));
    }
}
