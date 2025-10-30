package com.neocare.api.interfaces.controller;

import com.neocare.api.interfaces.dto.input.MedicaoEstresseInDto;
import com.neocare.api.interfaces.dto.output.MedicaoEstresseOutDto;

public interface MedicaoController {

    /**
     * Registra uma nova medição de estresse.
     *
     * @param medicaoEstresseInDto Dados de entrada da medição de estresse.
     * @return Dados de saída da medição de estresse registrada.
     */
    MedicaoEstresseOutDto registrarMedicaoEstresse(MedicaoEstresseInDto medicaoEstresseInDto);
}
