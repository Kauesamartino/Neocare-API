package com.neocare.api.interfaces.dto.output;

import com.neocare.api.domain.enums.TipoDispositivo;

public record DispositivoOutDto(
        Long id,
        Long usuarioId,
        TipoDispositivo tipoDispositivo,
        String enderecoDisp,
        Boolean ativo
) {
}
