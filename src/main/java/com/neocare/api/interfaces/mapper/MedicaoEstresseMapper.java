package com.neocare.api.interfaces.mapper;

import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.interfaces.dto.input.MedicaoEstresseInDto;

public final class MedicaoEstresseMapper {
    public static MedicaoEstresse toModel(MedicaoEstresseInDto medicaoEstresseInDto) {
        return new MedicaoEstresse(
                medicaoEstresseInDto.idUsuario(),
                medicaoEstresseInDto.idDispositivo(),
                medicaoEstresseInDto.tipoMedicao(),
                medicaoEstresseInDto.variacaoFrequenciaCardiaca(),
                medicaoEstresseInDto.condutividadePele()
        );
    }
}
