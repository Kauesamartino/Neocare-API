package com.neocare.api.interfaces.dto.output;

import com.neocare.api.domain.enums.TipoMedicao;

import java.time.LocalDateTime;

public class MedicaoOutDto {

    private Long id;
    private String nomeUsuario;
    private DispositivoMedicaoOutDto dispositivo;
    private LocalDateTime dataMedicao;
    private TipoMedicao tipoMedicao;

    public MedicaoOutDto(Long id, String nomeUsuario, DispositivoMedicaoOutDto dispositivoMedicaoOutDto, LocalDateTime dataMedicao, TipoMedicao tipoMedicao) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.dispositivo = dispositivoMedicaoOutDto;
        this.dataMedicao = dataMedicao;
        this.tipoMedicao = tipoMedicao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public DispositivoMedicaoOutDto getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoMedicaoOutDto dispositivo) {
        this.dispositivo = dispositivo;
    }

    public LocalDateTime getDataMedicao() {
        return dataMedicao;
    }

    public void setDataMedicao(LocalDateTime dataMedicao) {
        this.dataMedicao = dataMedicao;
    }

    public TipoMedicao getTipoMedicao() {
        return tipoMedicao;
    }

    public void setTipoMedicao(TipoMedicao tipoMedicao) {
        this.tipoMedicao = tipoMedicao;
    }
}
