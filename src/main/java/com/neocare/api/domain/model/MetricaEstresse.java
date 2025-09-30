package com.neocare.api.domain.model;

import java.time.LocalDateTime;

public class MetricaEstresse {

    private Long id;

    private Long medicaoEstresseId;

    private Integer indiceEstresse;

    private Categoria categoria;

    private LocalDateTime dataMetrica;

    public enum Categoria {
        AGUDO,
        CRONICO,
        EPISODICO_AGUDO,
        EUSTRESS,
        TRAUMATICO,
        AMBIENTAL,
        PSICOLOGICO,
        FISIOLOGICO
    }
}
