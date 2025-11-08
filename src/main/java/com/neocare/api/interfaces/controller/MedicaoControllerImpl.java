package com.neocare.api.interfaces.controller;

import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCase;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.interfaces.dto.input.MedicaoEstresseInDto;
import com.neocare.api.interfaces.dto.output.MedicaoEstresseOutDto;
import com.neocare.api.interfaces.mapper.MedicaoEstresseMapper;


public final class MedicaoControllerImpl implements  MedicaoController {

    private final RegistrarMedicaoEstresseUseCase registrarMedicaoEstresse;

    public MedicaoControllerImpl(RegistrarMedicaoEstresseUseCase registrarMedicaoEstresse) {
        this.registrarMedicaoEstresse = registrarMedicaoEstresse;
    }

    @Override
    public MedicaoEstresseOutDto registrarMedicaoEstresse(MedicaoEstresseInDto medicaoEstresseInDto) {
        MedicaoEstresse medicaoEstresse = MedicaoEstresseMapper.toModel(medicaoEstresseInDto);
        MedicaoEstresse registeredMedicaoEstresse = registrarMedicaoEstresse.execute(medicaoEstresse);
        return MedicaoEstresseMapper.toOutDto(registeredMedicaoEstresse);
    }

}
