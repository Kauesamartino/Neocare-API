package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.model.MedicaoEstresse;

import java.util.List;

public interface ListarMedicoesEstresseUseCase {
    List<MedicaoEstresse> porUsuario(Long usuarioId);
}
