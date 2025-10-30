package com.neocare.api.interfaces.dto.input;

import com.neocare.api.domain.enums.TipoMedicao;

public record MedicaoEstresseInDto(
        Long idUsuario,
        Long idDispositivo,
        TipoMedicao tipoMedicao,
        Double variacaoFrequenciaCardiaca,
        Double condutividadePele
) {
}
