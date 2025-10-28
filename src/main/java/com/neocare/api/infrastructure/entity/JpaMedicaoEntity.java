package com.neocare.api.infrastructure.entity;

import com.neocare.api.domain.enums.TipoMedicao;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicoes")
@Inheritance(strategy = InheritanceType.JOINED)
public class JpaMedicaoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private JpaUsuarioEntity jpaUsuarioEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispositivo_id")
    private JpaDispositivoEntity jpaDispositivoEntity;

    private LocalDateTime dataMedicao;

    private TipoMedicao tipoMedicao;

    public JpaMedicaoEntity() {
    }

    public JpaMedicaoEntity(JpaUsuarioEntity jpaUsuarioEntity, JpaDispositivoEntity jpaDispositivoEntity, LocalDateTime dataMedicao, TipoMedicao tipoMedicao) {
        this.jpaUsuarioEntity = jpaUsuarioEntity;
        this.jpaDispositivoEntity = jpaDispositivoEntity;
        this.dataMedicao = dataMedicao;
        this.tipoMedicao = tipoMedicao;
    }

    public JpaUsuarioEntity getJpaUsuarioEntity() {
        return jpaUsuarioEntity;
    }

    public void setJpaUsuarioEntity(JpaUsuarioEntity jpaUsuarioEntity) {
        this.jpaUsuarioEntity = jpaUsuarioEntity;
    }

    public JpaDispositivoEntity getJpaDispositivoEntity() {
        return jpaDispositivoEntity;
    }

    public void setJpaDispositivoEntity(JpaDispositivoEntity jpaDispositivoEntity) {
        this.jpaDispositivoEntity = jpaDispositivoEntity;
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
