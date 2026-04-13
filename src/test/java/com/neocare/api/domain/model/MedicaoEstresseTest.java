package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoMedicao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MedicaoEstresseTest {

    @Test
    @DisplayName("Deve criar medição de estresse com construtor básico")
    void deveCriarMedicaoEstresseBasica() {
        MedicaoEstresse medicao = new MedicaoEstresse(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);

        assertEquals(1L, medicao.getIdUsuario());
        assertEquals(1L, medicao.getIdDispositivo());
        assertEquals(TipoMedicao.MEDICAO_ESTRESSE, medicao.getTipoMedicao());
        assertEquals(50.0, medicao.getVariacaoFrequenciaCardiaca());
        assertEquals(5.0, medicao.getCondutividadePele());
        assertNotNull(medicao.getDataMedicao());
    }

    @Test
    @DisplayName("Deve criar medição de estresse com construtor completo")
    void deveCriarMedicaoEstresseCompleta() {
        LocalDateTime agora = LocalDateTime.now();
        MedicaoEstresse medicao = new MedicaoEstresse(1L, 1L, 1L, agora, TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);

        assertEquals(1L, medicao.getId());
        assertEquals(1L, medicao.getIdUsuario());
        assertEquals(agora, medicao.getDataMedicao());
    }

    @Test
    @DisplayName("Deve permitir valores nulos para HRV e GSR")
    void devePermitirValoresNulos() {
        MedicaoEstresse medicao = new MedicaoEstresse(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, null, null);

        assertNull(medicao.getVariacaoFrequenciaCardiaca());
        assertNull(medicao.getCondutividadePele());
    }

    @Test
    @DisplayName("Deve alterar valores com setters")
    void deveAlterarComSetters() {
        MedicaoEstresse medicao = new MedicaoEstresse(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);
        medicao.setVariacaoFrequenciaCardiaca(30.0);
        medicao.setCondutividadePele(8.0);

        assertEquals(30.0, medicao.getVariacaoFrequenciaCardiaca());
        assertEquals(8.0, medicao.getCondutividadePele());
    }
}
