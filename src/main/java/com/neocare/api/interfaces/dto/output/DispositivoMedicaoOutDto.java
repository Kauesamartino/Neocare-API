package com.neocare.api.interfaces.dto.output;

import com.neocare.api.domain.enums.TipoDispositivo;

public class DispositivoMedicaoOutDto {
    private Long id;
    private TipoDispositivo tipoDispositivo;
    private String enderecoDisp;
    private Boolean ativo;
}
