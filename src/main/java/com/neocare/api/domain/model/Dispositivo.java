package com.neocare.api.domain.model;

import com.neocare.api.domain.exception.ValidacaoDominioException;
import com.neocare.api.domain.enums.TipoDispositivo;

public class Dispositivo {
    private Long id;

    private Long usuarioId;

    private TipoDispositivo tipoDispositivo;

    private String enderecoDisp;

    private Boolean ativo;

    public Dispositivo() {
    }

    public Dispositivo(Long usuarioId, TipoDispositivo tipoDispositivo, String enderecoDisp) {
        setUsuarioId(usuarioId);
        setTipoDispositivo(tipoDispositivo);
        setEnderecoDisp(enderecoDisp);
        this.ativo = true;
    }

    public Dispositivo(Long usuarioId, TipoDispositivo tipoDispositivo, String enderecoDisp, Boolean ativo) {
        setUsuarioId(usuarioId);
        setTipoDispositivo(tipoDispositivo);
        setEnderecoDisp(enderecoDisp);
        this.ativo = ativo;
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

    public void setEnderecoDisp(String enderecoDisp) {
        this.enderecoDisp = enderecoDisp;
        if (enderecoDisp == null || enderecoDisp.isEmpty()) {
            throw new ValidacaoDominioException("Número de série não pode ser vazio");
        }
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    public String getEnderecoDisp() {
        return enderecoDisp;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Long getId() {
        return id;
    }
}
