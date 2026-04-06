package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.Alerta;

import java.util.List;

public interface AlertaRepository {
    Alerta save(Alerta alerta);

    List<Alerta> findByUsuarioId(Long usuarioId);

    List<Alerta> findAll();
}
