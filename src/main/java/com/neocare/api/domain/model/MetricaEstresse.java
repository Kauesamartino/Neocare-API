package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.Categoria;

import java.time.LocalDateTime;

public class MetricaEstresse {

    private Long id;

    private Long medicaoEstresseId;

    private Integer indiceEstresse;

    private Categoria categoria;

    private LocalDateTime dataMetrica;

    public MetricaEstresse(Long medicaoEstresseId, Integer indiceEstresse, Categoria categoria) {
        this.medicaoEstresseId = medicaoEstresseId;
        this.indiceEstresse = indiceEstresse;
        this.categoria = categoria;
        this.dataMetrica = LocalDateTime.now();
    }

    public MetricaEstresse(Long id, Long medicaoEstresseId, Integer indiceEstresse, Categoria categoria, LocalDateTime dataMetrica) {
        this.id = id;
        this.medicaoEstresseId = medicaoEstresseId;
        this.indiceEstresse = indiceEstresse;
        this.categoria = categoria;
        this.dataMetrica = dataMetrica;
    }

    /**
     * Calcula o índice de estresse (0-100) a partir dos valores de HRV e GSR.
     *
     * HRV (variação da frequência cardíaca): valores mais baixos = mais estresse.
     * Faixa de referência: 20-100 ms. Abaixo de 20 é crítico.
     *
     * GSR (condutividade da pele): valores mais altos = mais estresse.
     * Faixa de referência: 1-10 μS. Acima de 10 é crítico.
     *
     * O índice combina ambas as métricas com peso igual (50/50).
     */
    public static MetricaEstresse calcular(Long medicaoEstresseId, Double hrv, Double gsr) {
        int componenteHrv = calcularComponenteHrv(hrv);
        int componenteGsr = calcularComponenteGsr(gsr);

        int indice = (componenteHrv + componenteGsr) / 2;
        indice = Math.max(0, Math.min(100, indice));

        Categoria categoria = classificar(indice);

        return new MetricaEstresse(medicaoEstresseId, indice, categoria);
    }

    private static int calcularComponenteHrv(Double hrv) {
        if (hrv == null || hrv <= 0) {
            return 100;
        }
        if (hrv >= 80.0) {
            return 0;
        }
        if (hrv >= 60.0) {
            return 15;
        }
        if (hrv >= 40.0) {
            return 35;
        }
        if (hrv >= 20.0) {
            return 60;
        }
        if (hrv >= 15.0) {
            return 80;
        }
        return 100;
    }

    private static int calcularComponenteGsr(Double gsr) {
        if (gsr == null || gsr <= 0) {
            return 0;
        }
        if (gsr <= 2.0) {
            return 0;
        }
        if (gsr <= 5.0) {
            return 15;
        }
        if (gsr <= 8.0) {
            return 35;
        }
        if (gsr <= 10.0) {
            return 60;
        }
        if (gsr <= 15.0) {
            return 80;
        }
        return 100;
    }

    private static Categoria classificar(int indice) {
        if (indice <= 15) {
            return Categoria.EUSTRESS;
        }
        if (indice <= 35) {
            return Categoria.AMBIENTAL;
        }
        if (indice <= 50) {
            return Categoria.PSICOLOGICO;
        }
        if (indice <= 65) {
            return Categoria.FISIOLOGICO;
        }
        if (indice <= 80) {
            return Categoria.EPISODICO_AGUDO;
        }
        if (indice <= 90) {
            return Categoria.AGUDO;
        }
        return Categoria.CRONICO;
    }

    public Long getId() {
        return id;
    }

    public Long getMedicaoEstresseId() {
        return medicaoEstresseId;
    }

    public Integer getIndiceEstresse() {
        return indiceEstresse;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getDataMetrica() {
        return dataMetrica;
    }
}
