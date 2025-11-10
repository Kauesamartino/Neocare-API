package com.neocare.api.interfaces.dto.output;

import com.neocare.api.domain.enums.TipoDispositivo;

public class DispositivoMedicaoOutDto {
    private Long id;
    private TipoDispositivo tipoDispositivo;
    private String enderecoDisp;
    private Boolean ativo;

    public DispositivoMedicaoOutDto(Long id, TipoDispositivo tipoDispositivo, String enderecoDisp, Boolean ativo) {
        this.id = id;
        this.tipoDispositivo = tipoDispositivo;
        this.enderecoDisp = enderecoDisp;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
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
}
