package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.MedicaoVital;

import java.util.List;

public interface MedicaoVitalRepository {

    MedicaoVital save(MedicaoVital medicaoVital);

    List<MedicaoVital> findByUsuarioId(Long usuarioId);
}
