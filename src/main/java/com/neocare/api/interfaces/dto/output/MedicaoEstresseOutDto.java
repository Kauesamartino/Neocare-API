package com.neocare.api.interfaces.dto.output;

public class MedicaoEstresseOutDto {
    private Double variacaoFrequenciaCardiaca;
    private Double condutividadePele;
    private MedicaoOutDto medicaoOutDto;

    public MedicaoEstresseOutDto(Double variacaoFrequenciaCardiaca, Double condutividadePele, MedicaoOutDto medicaoOutDto) {
        this.variacaoFrequenciaCardiaca = variacaoFrequenciaCardiaca;
        this.condutividadePele = condutividadePele;
        this.medicaoOutDto = medicaoOutDto;
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

    public MedicaoOutDto getMedicaoOutDto() {
        return medicaoOutDto;
    }

    public void setMedicaoOutDto(MedicaoOutDto medicaoOutDto) {
        this.medicaoOutDto = medicaoOutDto;
    }
}
