package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.model.MedicaoEstresse;

public interface RegistrarMedicaoEstresseUseCase {
    MedicaoEstresse execute(MedicaoEstresse medicaoEstresse);
}
