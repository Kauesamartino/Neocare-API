package com.neocare.api.interfaces.mapper;

import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.interfaces.dto.input.MedicaoEstresseInDto;
import com.neocare.api.interfaces.dto.output.DispositivoMedicaoOutDto;
import com.neocare.api.interfaces.dto.output.MedicaoEstresseOutDto;
import com.neocare.api.interfaces.dto.output.MedicaoOutDto;

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

    public static MedicaoEstresseOutDto toOutDto(MedicaoEstresse registeredMedicaoEstresse, String nomeUsuario, Dispositivo dispositivo) {
        DispositivoMedicaoOutDto dispositivoMedicaoOutDto = new DispositivoMedicaoOutDto(
                dispositivo.getId(),
                dispositivo.getTipoDispositivo(),
                dispositivo.getEnderecoDisp(),
                dispositivo.getAtivo()
        );

        MedicaoOutDto medicaoOutDto = new MedicaoOutDto(
                registeredMedicaoEstresse.getId(),
                nomeUsuario,
                dispositivoMedicaoOutDto,
                registeredMedicaoEstresse.getDataMedicao(),
                registeredMedicaoEstresse.getTipoMedicao()
        );

        return new MedicaoEstresseOutDto(
                registeredMedicaoEstresse.getVariacaoFrequenciaCardiaca(),
                registeredMedicaoEstresse.getCondutividadePele(),
                medicaoOutDto
        );
    }
}
