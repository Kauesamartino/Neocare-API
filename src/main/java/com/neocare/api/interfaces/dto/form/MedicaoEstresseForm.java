package com.neocare.api.interfaces.dto.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MedicaoEstresseForm {

    @NotNull(message = "ID do dispositivo é obrigatório")
    @Positive(message = "ID do dispositivo deve ser positivo")
    private Long idDispositivo;

    private Double variacaoFrequenciaCardiaca;

    private Double condutividadePele;

    public MedicaoEstresseForm() {
    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Double getVariacaoFrequenciaCardiaca() {
        return variacaoFrequenciaCardiaca;
    }

    public void setVariacaoFrequenciaCardiaca(Double variacaoFrequenciaCardiaca) {
        this.variacaoFrequenciaCardiaca = variacaoFrequenciaCardiaca;
    }

    public Double getCondutividadePele() {
        return condutividadePele;
    }

    public void setCondutividadePele(Double condutividadePele) {
        this.condutividadePele = condutividadePele;
    }
}
