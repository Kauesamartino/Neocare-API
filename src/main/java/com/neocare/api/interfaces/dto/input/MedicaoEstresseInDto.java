package com.neocare.api.interfaces.dto.input;

import com.neocare.api.domain.enums.TipoMedicao;
import jakarta.validation.constraints.NotNull;

public record MedicaoEstresseInDto(
        @NotNull(message = "ID do usuário é obrigatório")
        Long idUsuario,
        @NotNull(message = "ID do dispositivo é obrigatório")
        Long idDispositivo,
        @NotNull(message = "Tipo de medição é obrigatório")
        TipoMedicao tipoMedicao,
        Double variacaoFrequenciaCardiaca,
        Double condutividadePele
) {
}
