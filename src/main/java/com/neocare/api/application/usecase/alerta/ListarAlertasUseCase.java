package com.neocare.api.application.usecase.alerta;

import com.neocare.api.domain.model.Alerta;

import java.util.List;

public interface ListarAlertasUseCase {
    List<Alerta> porUsuario(Long usuarioId);

    List<Alerta> todos();
}
