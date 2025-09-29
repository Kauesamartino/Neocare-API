package com.braincare.api.domain.model;

import java.time.LocalDateTime;

public class Medicao {

    private Long id;

    private Long usuarioId;

    private Long dispositivoId;

    private LocalDateTime dataMedicao;

    private TipoMedicao tipoMedicao;

    public enum TipoMedicao {
        MEDICAO_VITAL,
        MEDICAO_ESTRESSE
    }

}
