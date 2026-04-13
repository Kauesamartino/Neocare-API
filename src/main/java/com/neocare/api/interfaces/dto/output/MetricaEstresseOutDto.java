package com.neocare.api.interfaces.dto.output;

import com.neocare.api.domain.enums.Categoria;

import java.time.LocalDateTime;

public record MetricaEstresseOutDto(
        Long id,
        Long medicaoEstresseId,
        Integer indiceEstresse,
        Categoria categoria,
        LocalDateTime dataMetrica
) {
}
