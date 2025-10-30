package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.Medicao;

public interface MedicaoRepository {
    Medicao save(Medicao medicao);

    Medicao findById(Integer id);
}
