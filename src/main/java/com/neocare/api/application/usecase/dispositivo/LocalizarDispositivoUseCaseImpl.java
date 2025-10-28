package com.neocare.api.application.usecase.dispositivo;

import com.neocare.api.application.exception.EntidadeNaoEncontradaException;
import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.domain.repository.DispositivoRepository;
import jakarta.persistence.EntityNotFoundException;

public final class LocalizarDispositivoUseCaseImpl implements LocalizarDispositivoUseCase{

    private final DispositivoRepository dispositivoRepository;

    public LocalizarDispositivoUseCaseImpl(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    @Override
    public Dispositivo findById(Long id) {
        try{
            return dispositivoRepository.findById(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntityNotFoundException("Dipositivo não encontrado");
        }
    }
}
