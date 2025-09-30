package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;

import java.time.LocalDateTime;

public class Medicao {

    private Long id;

    private Long usuarioId;

    private Long dispositivoId;

    private LocalDateTime dataMedicao;

    private TipoMedicao tipoMedicao;

}
