package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.Categoria;

import java.time.LocalDateTime;

public class MetricaEstresse {

    private Long id;

    private Long medicaoEstresseId;

    private Integer indiceEstresse;

    private Categoria categoria;

    private LocalDateTime dataMetrica;
}
