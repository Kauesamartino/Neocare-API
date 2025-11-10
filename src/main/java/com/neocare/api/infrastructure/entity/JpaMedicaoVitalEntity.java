package com.neocare.api.infrastructure.entity;

import com.neocare.api.domain.enums.TipoMedicao;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "medicoes_vitais")
@Entity
@PrimaryKeyJoinColumn(name = "medicao_id")
public class JpaMedicaoVitalEntity extends JpaMedicaoEntity {

    private Integer batimentosPorMinuto;

    private Double oxigenacaoSangue;

    private Integer pressaoSistolica;

    private Integer pressaoDiastolica;

    public JpaMedicaoVitalEntity() {
    }

    public JpaMedicaoVitalEntity(JpaUsuarioEntity jpaUsuarioEntity, JpaDispositivoEntity jpaDispositivoEntity, LocalDateTime dataMedicao, TipoMedicao tipoMedicao, Integer batimentosPorMinuto, Double oxigenacaoSangue, Integer pressaoSistolica, Integer pressaoDiastolica) {
        super(jpaUsuarioEntity, jpaDispositivoEntity, dataMedicao, tipoMedicao);
        this.batimentosPorMinuto = batimentosPorMinuto;
        this.oxigenacaoSangue = oxigenacaoSangue;
        this.pressaoSistolica = pressaoSistolica;
        this.pressaoDiastolica = pressaoDiastolica;
    }

    public Integer getBatimentosPorMinuto() {
        return batimentosPorMinuto;
    }

    public void setBatimentosPorMinuto(Integer batimentosPorMinuto) {
        this.batimentosPorMinuto = batimentosPorMinuto;
    }

    public Double getOxigenacaoSangue() {
        return oxigenacaoSangue;
    }

    public void setOxigenacaoSangue(Double oxigenacaoSangue) {
        this.oxigenacaoSangue = oxigenacaoSangue;
    }

    public Integer getPressaoSistolica() {
        return pressaoSistolica;
    }

    public void setPressaoSistolica(Integer pressaoSistolica) {
        this.pressaoSistolica = pressaoSistolica;
    }

    public Integer getPressaoDiastolica() {
        return pressaoDiastolica;
    }

    public void setPressaoDiastolica(Integer pressaoDiastolica) {
        this.pressaoDiastolica = pressaoDiastolica;
    }
}
