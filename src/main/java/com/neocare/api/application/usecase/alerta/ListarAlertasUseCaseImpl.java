package com.neocare.api.application.usecase.alerta;

import com.neocare.api.domain.model.Alerta;
import com.neocare.api.domain.repository.AlertaRepository;

import java.util.List;

public final class ListarAlertasUseCaseImpl implements ListarAlertasUseCase {

    private final AlertaRepository alertaRepository;

    public ListarAlertasUseCaseImpl(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    @Override
    public List<Alerta> porUsuario(Long usuarioId) {
        return alertaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Alerta> todos() {
        return alertaRepository.findAll();
    }
}
