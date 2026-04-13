package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.Severidade;
import com.neocare.api.domain.enums.TipoAlerta;
import com.neocare.api.domain.enums.TipoMedicao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AlertaAvaliarVitalTest {

    private MedicaoVital criarMedicaoVital(Long id, Integer bpm, Double spo2,
                                           Integer sistolica, Integer diastolica) {
        return new MedicaoVital(id, 1L, 1L, LocalDateTime.now(),
                TipoMedicao.MEDICAO_VITAL, bpm, spo2, sistolica, diastolica);
    }

    @Test
    @DisplayName("Não deve gerar alerta quando sinais vitais estão normais")
    void naoDeveGerarAlertaQuandoVitaisNormais() {
        MedicaoVital medicao = criarMedicaoVital(1L, 80, 98.0, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve gerar alerta MODERADA quando BPM > 150")
    void deveGerarAlertaModeradaQuandoBpmAlto() {
        MedicaoVital medicao = criarMedicaoVital(1L, 160, 98.0, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);

        assertTrue(resultado.isPresent());
        Alerta alerta = resultado.get();
        assertEquals(Severidade.MODERADA, alerta.getSeveridade());
        assertEquals(TipoAlerta.AVC, alerta.getTipoAlerta());
    }

    @Test
    @DisplayName("Deve gerar alerta MODERADA quando BPM < 40")
    void deveGerarAlertaModeradaQuandoBpmBaixo() {
        MedicaoVital medicao = criarMedicaoVital(1L, 35, 98.0, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);

        assertTrue(resultado.isPresent());
        assertEquals(Severidade.MODERADA, resultado.get().getSeveridade());
    }

    @Test
    @DisplayName("Deve gerar alerta ALTA quando SpO2 < 92")
    void deveGerarAlertaAltaQuandoSpo2Critico() {
        MedicaoVital medicao = criarMedicaoVital(1L, 80, 89.0, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);

        assertTrue(resultado.isPresent());
        assertEquals(Severidade.ALTA, resultado.get().getSeveridade());
    }

    @Test
    @DisplayName("Deve gerar alerta ALTA quando pressão sistólica > 180")
    void deveGerarAlertaAltaQuandoPressaoCritica() {
        MedicaoVital medicao = criarMedicaoVital(1L, 80, 98.0, 200, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);

        assertTrue(resultado.isPresent());
        assertEquals(Severidade.ALTA, resultado.get().getSeveridade());
    }

    @Test
    @DisplayName("Deve gerar alerta ALTA quando múltiplos indicadores críticos")
    void deveGerarAlertaAltaQuandoMultiplosCriticos() {
        MedicaoVital medicao = criarMedicaoVital(1L, 160, 89.0, 200, 100);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);

        assertTrue(resultado.isPresent());
        assertEquals(Severidade.ALTA, resultado.get().getSeveridade());
    }

    @Test
    @DisplayName("Não deve gerar alerta quando BPM é nulo e demais normais")
    void naoDeveGerarAlertaQuandoBpmNuloENormais() {
        MedicaoVital medicao = criarMedicaoVital(1L, null, 98.0, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Não deve gerar alerta quando SpO2 é nulo e demais normais")
    void naoDeveGerarAlertaQuandoSpo2NuloENormais() {
        MedicaoVital medicao = criarMedicaoVital(1L, 80, null, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Não deve gerar alerta quando sistólica é nula e demais normais")
    void naoDeveGerarAlertaQuandoSistolicaNulaENormais() {
        MedicaoVital medicao = criarMedicaoVital(1L, 80, 98.0, null, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Não deve gerar alerta quando BPM exatamente 150 (limiar não inclusivo)")
    void naoDeveGerarAlertaQuandoBpmNoLimite150() {
        MedicaoVital medicao = criarMedicaoVital(1L, 150, 98.0, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Não deve gerar alerta quando BPM exatamente 40 (limiar não inclusivo)")
    void naoDeveGerarAlertaQuandoBpmNoLimite40() {
        MedicaoVital medicao = criarMedicaoVital(1L, 40, 98.0, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve incluir valores detectados no alerta")
    void deveIncluirValoresDetectados() {
        MedicaoVital medicao = criarMedicaoVital(1L, 160, 89.0, 200, 100);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);

        assertTrue(resultado.isPresent());
        String valor = resultado.get().getValorDetectado();
        assertTrue(valor.contains("BPM=160"));
        assertTrue(valor.contains("SpO2=89.0"));
        assertTrue(valor.contains("PA=200/100"));
    }

    @Test
    @DisplayName("Deve associar alerta ao usuario e medicao corretos")
    void deveAssociarAlertaAoUsuarioEMedicaoCorretos() {
        MedicaoVital medicao = new MedicaoVital(10L, 5L, 3L, LocalDateTime.now(),
                TipoMedicao.MEDICAO_VITAL, 160, 98.0, 120, 80);
        Optional<Alerta> resultado = Alerta.avaliarVital(medicao);

        assertTrue(resultado.isPresent());
        assertEquals(5L, resultado.get().getUsuarioId());
        assertEquals(10L, resultado.get().getMedicaoId());
    }
}
