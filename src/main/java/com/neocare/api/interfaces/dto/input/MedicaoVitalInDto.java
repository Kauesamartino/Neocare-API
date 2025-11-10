package com.neocare.api.interfaces.dto.input;

import com.neocare.api.domain.enums.TipoMedicao;

public record MedicaoVitalInDto(
        Long idUsuario,
        Long idDispositivo,
        TipoMedicao tipoMedicao,
        Integer batimentosPorMinuto,
        Double oxigenacaoSangue,
        Integer pressaoSistolica,
        Integer pressaoDiastolica
){

}
