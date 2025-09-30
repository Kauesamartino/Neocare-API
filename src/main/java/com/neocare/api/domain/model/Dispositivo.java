package com.neocare.api.domain.model;

import com.neocare.api.domain.exception.ValidacaoDominioException;
import com.neocare.api.domain.enums.TipoDispositivo;

public class Dispositivo {

    private Long id;

    private Long usuarioId;

    private TipoDispositivo tipoDispositivo;

    private Integer numeroSerie;

    private Boolean ativo;

    public Dispositivo(Long usuarioId, TipoDispositivo tipoDispositivo, Integer numeroSerie) {
        setUsuarioId(usuarioId);
        setTipoDispositivo(tipoDispositivo);
        setNumeroSerie(numeroSerie);
        this.ativo = true;
    }

    public void setUsuarioId(Long usuarioId) {
        if (usuarioId == null) {
            throw new ValidacaoDominioException("Id do usuário não pode ser nulo");
        }
        this.usuarioId = usuarioId;
    }

    public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
        if (tipoDispositivo == null || tipoDispositivo.describeConstable().isEmpty()) {
            throw new ValidacaoDominioException("Tipo de dispositivo não pode ser nulo ou vazio");
        }
    }

    public void setNumeroSerie(Integer numeroSerie) {
        this.numeroSerie = numeroSerie;
        if (numeroSerie == null) {
            throw new ValidacaoDominioException("Número de série não pode ser vazio");
        }
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    public Integer getNumeroSerie() {
        return numeroSerie;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
