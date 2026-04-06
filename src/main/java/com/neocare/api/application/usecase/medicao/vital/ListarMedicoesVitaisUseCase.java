package com.neocare.api.application.usecase.medicao.vital;

import com.neocare.api.domain.model.MedicaoVital;

import java.util.List;

public interface ListarMedicoesVitaisUseCase {
    List<MedicaoVital> porUsuario(Long usuarioId);
}
