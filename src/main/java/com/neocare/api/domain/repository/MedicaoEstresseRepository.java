package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.MedicaoEstresse;

import java.util.List;
import java.util.Optional;

public interface MedicaoEstresseRepository {
    MedicaoEstresse save(MedicaoEstresse medicaoEstresse);

    List<MedicaoEstresse> findByUsuarioId(Long usuarioId);

    Optional<MedicaoEstresse> findById(Long id);
}
