package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;

import java.time.LocalDateTime;


public class MedicaoVital extends Medicao {

    private Integer batimentosPorMinuto;

    private Double oxigenacaoSangue;

    private Integer pressaoSistolica;

    private Integer pressaoDiastolica;

    public MedicaoVital(Long idUsuario, Long idDispositivo, TipoMedicao tipoMedicao, Integer batimentosPorMinuto, Double oxigenacaoSangue, Integer pressaoSistolica, Integer pressaoDiastolica) {
        super(idUsuario, idDispositivo, tipoMedicao);
        this.batimentosPorMinuto = batimentosPorMinuto;
        this.oxigenacaoSangue = oxigenacaoSangue;
        this.pressaoSistolica = pressaoSistolica;
        this.pressaoDiastolica = pressaoDiastolica;
    }

    public MedicaoVital(Long id, Long idUsuario, Long idDispositivo, LocalDateTime dataMedicao, TipoMedicao tipoMedicao, Integer batimentosPorMinuto, Double oxigenacaoSangue, Integer pressaoSistolica, Integer pressaoDiastolica) {
        super(id, idUsuario, idDispositivo, dataMedicao, tipoMedicao);
        this.batimentosPorMinuto = batimentosPorMinuto;
        this.oxigenacaoSangue = oxigenacaoSangue;
        this.pressaoSistolica = pressaoSistolica;
        this.pressaoDiastolica = pressaoDiastolica;
    }

    public Integer getBatimentosPorMinuto() {
        return batimentosPorMinuto;
    }

    public Double getOxigenacaoSangue() {
        return oxigenacaoSangue;
    }

    public Integer getPressaoSistolica() {
        return pressaoSistolica;
    }

    public Integer getPressaoDiastolica() {
        return pressaoDiastolica;
    }
}
