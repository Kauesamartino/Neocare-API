package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MedicaoVitalTest {

    @Test
    @DisplayName("Deve criar medição vital com construtor básico")
    void deveCriarMedicaoVitalBasica() {
        MedicaoVital medicao = new MedicaoVital(1L, 1L, TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80);

        assertEquals(1L, medicao.getIdUsuario());
        assertEquals(1L, medicao.getIdDispositivo());
        assertEquals(TipoMedicao.MEDICAO_VITAL, medicao.getTipoMedicao());
        assertEquals(80, medicao.getBatimentosPorMinuto());
        assertEquals(98.0, medicao.getOxigenacaoSangue());
        assertEquals(120, medicao.getPressaoSistolica());
        assertEquals(80, medicao.getPressaoDiastolica());
        assertNotNull(medicao.getDataMedicao());
    }

    @Test
    @DisplayName("Deve criar medição vital com construtor completo")
    void deveCriarMedicaoVitalCompleta() {
        LocalDateTime agora = LocalDateTime.now();
        MedicaoVital medicao = new MedicaoVital(1L, 1L, 1L, agora, TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80);

        assertEquals(1L, medicao.getId());
        assertEquals(1L, medicao.getIdUsuario());
        assertEquals(agora, medicao.getDataMedicao());
    }

    @Test
    @DisplayName("Deve permitir valores nulos para sinais vitais")
    void devePermitirValoresNulos() {
        MedicaoVital medicao = new MedicaoVital(1L, 1L, TipoMedicao.MEDICAO_VITAL, null, null, null, null);

        assertNull(medicao.getBatimentosPorMinuto());
        assertNull(medicao.getOxigenacaoSangue());
        assertNull(medicao.getPressaoSistolica());
        assertNull(medicao.getPressaoDiastolica());
    }
}
