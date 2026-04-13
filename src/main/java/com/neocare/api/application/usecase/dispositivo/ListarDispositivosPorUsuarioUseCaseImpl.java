package com.neocare.api.application.usecase.dispositivo;

import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.domain.repository.DispositivoRepository;

import java.util.List;

public final class ListarDispositivosPorUsuarioUseCaseImpl implements ListarDispositivosPorUsuarioUseCase {

    private final DispositivoRepository dispositivoRepository;

    public ListarDispositivosPorUsuarioUseCaseImpl(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    @Override
    public List<Dispositivo> execute(Long usuarioId) {
        return dispositivoRepository.findByUsuarioId(usuarioId);
    }
}
