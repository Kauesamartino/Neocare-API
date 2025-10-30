package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;


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
}
