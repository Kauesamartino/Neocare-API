package com.neocare.api.application.usecase.dispositivo;

import com.neocare.api.domain.model.Dispositivo;

import java.util.List;

public interface ListarDispositivosPorUsuarioUseCase {
    List<Dispositivo> execute(Long usuarioId);
}
