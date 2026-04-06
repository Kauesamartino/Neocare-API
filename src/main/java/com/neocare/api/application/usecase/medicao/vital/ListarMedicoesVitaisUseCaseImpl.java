package com.neocare.api.application.usecase.medicao.vital;

import com.neocare.api.domain.model.MedicaoVital;
import com.neocare.api.domain.repository.MedicaoVitalRepository;

import java.util.List;

public final class ListarMedicoesVitaisUseCaseImpl implements ListarMedicoesVitaisUseCase {

    private final MedicaoVitalRepository medicaoVitalRepository;

    public ListarMedicoesVitaisUseCaseImpl(MedicaoVitalRepository medicaoVitalRepository) {
        this.medicaoVitalRepository = medicaoVitalRepository;
    }

    @Override
    public List<MedicaoVital> porUsuario(Long usuarioId) {
        return medicaoVitalRepository.findByUsuarioId(usuarioId);
    }
}
