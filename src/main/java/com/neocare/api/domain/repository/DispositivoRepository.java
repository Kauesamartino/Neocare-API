package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.Dispositivo;

import java.util.List;

public interface DispositivoRepository {
    Dispositivo findById(Long id);

    List<Dispositivo> findByUsuarioId(Long usuarioId);
}
