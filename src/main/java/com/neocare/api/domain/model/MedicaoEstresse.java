package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;

import java.time.LocalDateTime;

public class MedicaoEstresse extends Medicao {

    private Double variacaoFrequenciaCardiaca;

    private Double condutividadePele;

    public MedicaoEstresse(Long idUsuario, Long idDispositivo, TipoMedicao tipoMedicao, Double variacaoFrequenciaCardiaca, Double condutividadePele) {
        super(idUsuario, idDispositivo, tipoMedicao);
        this.variacaoFrequenciaCardiaca = variacaoFrequenciaCardiaca;
        this.condutividadePele = condutividadePele;
    }

    public MedicaoEstresse(Long id, Long idUsuario, Long idDispositivo, LocalDateTime dataMedicao, TipoMedicao tipoMedicao, Double variacaoFrequenciaCardiaca, Double condutividadePele) {
        super(id, idUsuario, idDispositivo, dataMedicao, tipoMedicao);
        this.variacaoFrequenciaCardiaca = variacaoFrequenciaCardiaca;
        this.condutividadePele = condutividadePele;
    }

    public Double getVariacaoFrequenciaCardiaca() {
        return variacaoFrequenciaCardiaca;
    }

    public void setVariacaoFrequenciaCardiaca(Double variacaoFrequenciaCardiaca) {
        this.variacaoFrequenciaCardiaca = variacaoFrequenciaCardiaca;
    }

    public Double getCondutividadePele() {
        return condutividadePele;
    }

    public void setCondutividadePele(Double condutividadePele) {
        this.condutividadePele = condutividadePele;
    }
}
