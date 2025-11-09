package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;

import java.time.LocalDateTime;

public class Medicao {

    private Long id;

    private Long idUsuario;

    private Long idDispositivo;

    private LocalDateTime dataMedicao;

    private TipoMedicao tipoMedicao;

    public Medicao(Long idUsuario, Long idDispositivo, TipoMedicao tipoMedicao) {
        this.idUsuario = idUsuario;
        this.idDispositivo = idDispositivo;
        this.dataMedicao = LocalDateTime.now();
        this.tipoMedicao = tipoMedicao;
    }

    public Medicao(Long id, Long idUsuario, Long idDispositivo, LocalDateTime dataMedicao, TipoMedicao tipoMedicao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idDispositivo = idDispositivo;
        this.dataMedicao = dataMedicao;
        this.tipoMedicao = tipoMedicao;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public void setDataMedicao(LocalDateTime dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public void setTipoMedicao(TipoMedicao tipoMedicao) {
        this.tipoMedicao = tipoMedicao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public LocalDateTime getDataMedicao() {
        return dataMedicao;
    }

    public TipoMedicao getTipoMedicao() {
        return tipoMedicao;
    }

    public Long getId() {
        return id;
    }
}
