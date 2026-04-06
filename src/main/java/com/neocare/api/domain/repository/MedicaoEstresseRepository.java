package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.MedicaoEstresse;

import java.util.List;

public interface MedicaoEstresseRepository {
    MedicaoEstresse save(MedicaoEstresse medicaoEstresse);

    List<MedicaoEstresse> findByUsuarioId(Long usuarioId);
}
