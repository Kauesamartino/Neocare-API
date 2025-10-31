package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.model.MedicaoEstresse;

public interface LocalizarMedicaoEstresseUseCase {
    MedicaoEstresse execute(Long id);
}
