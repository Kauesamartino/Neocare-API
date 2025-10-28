package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.Dispositivo;

public interface DispositivoRepository {
    Dispositivo findById(Long id);
}
