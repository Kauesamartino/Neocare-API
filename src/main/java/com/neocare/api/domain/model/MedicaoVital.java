package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;

import java.time.LocalDateTime;

public class MedicaoVital extends Medicao {

    private Integer batimentosPorMinuto;

    private Double oxigenacaoSangue;

    private Integer pressaoSistolica;

    private Integer pressaoDiastolica;

    public MedicaoVital(String nomeUsuario, String tipoDispositivo, TipoMedicao tipoMedicao) {
        super(nomeUsuario, tipoDispositivo, tipoMedicao);
    }
}
