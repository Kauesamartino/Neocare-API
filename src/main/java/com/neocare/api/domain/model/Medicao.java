package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;

import java.time.LocalDateTime;

public class Medicao {

    private String nomeUsuario;

    private String tipoDispositivo;

    private LocalDateTime dataMedicao;

    private TipoMedicao tipoMedicao;

    public Medicao(String nomeUsuario, String tipoDispositivo, TipoMedicao tipoMedicao) {
        this.nomeUsuario = nomeUsuario;
        this.tipoDispositivo = tipoDispositivo;
        this.dataMedicao = LocalDateTime.now();
        this.tipoMedicao = tipoMedicao;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public void setDataMedicao(LocalDateTime dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public void setTipoMedicao(TipoMedicao tipoMedicao) {
        this.tipoMedicao = tipoMedicao;
    }
}
