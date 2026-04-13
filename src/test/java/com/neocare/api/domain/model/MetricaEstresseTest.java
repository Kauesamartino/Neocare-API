package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.Categoria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetricaEstresseTest {

    @Test
    @DisplayName("HRV alto e GSR baixo devem gerar índice baixo (EUSTRESS)")
    void deveSerEustressQuandoValoresRelaxados() {
        MetricaEstresse metrica = MetricaEstresse.calcular(1L, 85.0, 1.0);
        assertTrue(metrica.getIndiceEstresse() <= 15);
        assertEquals(Categoria.EUSTRESS, metrica.getCategoria());
    }

    @Test
    @DisplayName("HRV moderado e GSR moderado devem gerar estresse intermediário")
    void deveSerIntermediarioQuandoValoresModerados() {
        MetricaEstresse metrica = MetricaEstresse.calcular(1L, 45.0, 6.0);
        assertTrue(metrica.getIndiceEstresse() > 15);
        assertTrue(metrica.getIndiceEstresse() <= 50);
    }

    @Test
    @DisplayName("HRV muito baixo e GSR muito alto devem gerar índice alto (CRONICO)")
    void deveSerCronicoQuandoValoresCriticos() {
        MetricaEstresse metrica = MetricaEstresse.calcular(1L, 5.0, 20.0);
        assertEquals(100, metrica.getIndiceEstresse());
        assertEquals(Categoria.CRONICO, metrica.getCategoria());
    }

    @Test
    @DisplayName("HRV nulo deve resultar em componente máximo (100)")
    void hrvNuloDeveContribuirComMaximo() {
        MetricaEstresse metrica = MetricaEstresse.calcular(1L, null, 1.0);
        assertEquals(50, metrica.getIndiceEstresse());
    }

    @Test
    @DisplayName("GSR nulo deve resultar em componente mínimo (0)")
    void gsrNuloDeveContribuirComMinimo() {
        MetricaEstresse metrica = MetricaEstresse.calcular(1L, 85.0, null);
        assertEquals(0, metrica.getIndiceEstresse());
        assertEquals(Categoria.EUSTRESS, metrica.getCategoria());
    }

    @Test
    @DisplayName("Índice deve estar entre 0 e 100")
    void indiceDeveEstarEntre0E100() {
        MetricaEstresse metricaBaixa = MetricaEstresse.calcular(1L, 100.0, 0.5);
        MetricaEstresse metricaAlta = MetricaEstresse.calcular(2L, 5.0, 20.0);

        assertTrue(metricaBaixa.getIndiceEstresse() >= 0);
        assertTrue(metricaAlta.getIndiceEstresse() <= 100);
    }

    @Test
    @DisplayName("Deve associar ao ID da medição de estresse corretamente")
    void deveAssociarAoIdDaMedicao() {
        MetricaEstresse metrica = MetricaEstresse.calcular(42L, 50.0, 5.0);
        assertEquals(42L, metrica.getMedicaoEstresseId());
    }

    @Test
    @DisplayName("Deve classificar corretamente cada faixa de categoria")
    void deveClassificarCorretamente() {
        // EUSTRESS: 0-15
        MetricaEstresse eustress = MetricaEstresse.calcular(1L, 85.0, 1.5);
        assertEquals(Categoria.EUSTRESS, eustress.getCategoria());

        // CRONICO: > 90
        MetricaEstresse cronico = MetricaEstresse.calcular(2L, 5.0, 20.0);
        assertEquals(Categoria.CRONICO, cronico.getCategoria());
    }

    @Test
    @DisplayName("HRV entre 20-40 e GSR entre 8-10 devem gerar FISIOLOGICO")
    void deveSerFisiologicoEmFaixaMedia() {
        MetricaEstresse metrica = MetricaEstresse.calcular(1L, 30.0, 9.0);
        assertEquals(Categoria.FISIOLOGICO, metrica.getCategoria());
    }

    @Test
    @DisplayName("Deve preencher dataMetrica automaticamente")
    void devePreencherDataMetrica() {
        MetricaEstresse metrica = MetricaEstresse.calcular(1L, 50.0, 5.0);
        assertNotNull(metrica.getDataMetrica());
    }

    @Test
    @DisplayName("Construtor completo deve preservar todos os campos")
    void construtorCompletoDevePreservarCampos() {
        java.time.LocalDateTime agora = java.time.LocalDateTime.now();
        MetricaEstresse metrica = new MetricaEstresse(1L, 10L, 75, Categoria.EPISODICO_AGUDO, agora);

        assertEquals(1L, metrica.getId());
        assertEquals(10L, metrica.getMedicaoEstresseId());
        assertEquals(75, metrica.getIndiceEstresse());
        assertEquals(Categoria.EPISODICO_AGUDO, metrica.getCategoria());
        assertEquals(agora, metrica.getDataMetrica());
    }
}
