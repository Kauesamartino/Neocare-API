package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;

import java.util.List;

public final class ListarMedicoesEstresseUseCaseImpl implements ListarMedicoesEstresseUseCase {

    private final MedicaoEstresseRepository medicaoEstresseRepository;

    public ListarMedicoesEstresseUseCaseImpl(MedicaoEstresseRepository medicaoEstresseRepository) {
        this.medicaoEstresseRepository = medicaoEstresseRepository;
    }

    @Override
    public List<MedicaoEstresse> porUsuario(Long usuarioId) {
        return medicaoEstresseRepository.findByUsuarioId(usuarioId);
    }
}
