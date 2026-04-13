package com.neocare.api.interfaces.dto.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MedicaoVitalForm {

    @NotNull(message = "ID do dispositivo é obrigatório")
    @Positive(message = "ID do dispositivo deve ser positivo")
    private Long idDispositivo;

    private Integer batimentosPorMinuto;

    private Double oxigenacaoSangue;

    private Integer pressaoSistolica;

    private Integer pressaoDiastolica;

    public MedicaoVitalForm() {
    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Integer getBatimentosPorMinuto() {
        return batimentosPorMinuto;
    }

    public void setBatimentosPorMinuto(Integer batimentosPorMinuto) {
        this.batimentosPorMinuto = batimentosPorMinuto;
    }

    public Double getOxigenacaoSangue() {
        return oxigenacaoSangue;
    }

    public void setOxigenacaoSangue(Double oxigenacaoSangue) {
        this.oxigenacaoSangue = oxigenacaoSangue;
    }

    public Integer getPressaoSistolica() {
        return pressaoSistolica;
    }

    public void setPressaoSistolica(Integer pressaoSistolica) {
        this.pressaoSistolica = pressaoSistolica;
    }

    public Integer getPressaoDiastolica() {
        return pressaoDiastolica;
    }

    public void setPressaoDiastolica(Integer pressaoDiastolica) {
        this.pressaoDiastolica = pressaoDiastolica;
    }
}
