package com.neocare.api.infrastructure.entity;

import com.neocare.api.domain.enums.TipoDispositivo;
import jakarta.persistence.*;

@Table(name = "dispositivo")
@Entity
public class JpaDispositivoEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private JpaUsuarioEntity usuarioEntity;

    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipoDispositivo;

    private String enderecoDisp;

    private Boolean ativo;

    public JpaDispositivoEntity(JpaUsuarioEntity usuarioEntity, TipoDispositivo tipoDispositivo, String enderecoDisp, Boolean ativo) {
        this.usuarioEntity = usuarioEntity;
        this.tipoDispositivo = tipoDispositivo;
        this.enderecoDisp = enderecoDisp;
        this.ativo = ativo;
    }

    public JpaDispositivoEntity() {
    }

    public JpaUsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
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
