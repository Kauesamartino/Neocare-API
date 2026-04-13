package com.neocare.api.application.usecase.medicao.vital;

import com.neocare.api.domain.enums.TipoMedicao;
import com.neocare.api.domain.model.Alerta;
import com.neocare.api.domain.model.MedicaoVital;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.domain.repository.MedicaoVitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrarMedicaoVitalUseCaseTest {

    @Mock
    private MedicaoVitalRepository medicaoVitalRepository;

    @Mock
    private AlertaRepository alertaRepository;

    private RegistrarMedicaoVitalUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new RegistrarMedicaoVitalUseCaseImpl(medicaoVitalRepository, alertaRepository);
    }

    @Test
    @DisplayName("Deve salvar medição vital e retornar entidade salva")
    void deveSalvarMedicao() {
        MedicaoVital input = new MedicaoVital(1L, 1L, TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80);
        MedicaoVital salva = new MedicaoVital(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80);

        when(medicaoVitalRepository.save(input)).thenReturn(salva);

        MedicaoVital resultado = useCase.execute(input);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(medicaoVitalRepository).save(input);
    }

    @Test
    @DisplayName("Não deve gerar alerta quando sinais vitais normais")
    void naoDeveGerarAlertaQuandoVitaisNormais() {
        MedicaoVital input = new MedicaoVital(1L, 1L, TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80);
        MedicaoVital salva = new MedicaoVital(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80);

        when(medicaoVitalRepository.save(input)).thenReturn(salva);

        useCase.execute(input);

        verify(alertaRepository, never()).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve gerar alerta quando BPM crítico")
    void deveGerarAlertaQuandoBpmCritico() {
        MedicaoVital input = new MedicaoVital(1L, 1L, TipoMedicao.MEDICAO_VITAL, 160, 98.0, 120, 80);
        MedicaoVital salva = new MedicaoVital(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_VITAL, 160, 98.0, 120, 80);

        when(medicaoVitalRepository.save(input)).thenReturn(salva);

        useCase.execute(input);

        ArgumentCaptor<Alerta> alertaCaptor = ArgumentCaptor.forClass(Alerta.class);
        verify(alertaRepository).save(alertaCaptor.capture());

        Alerta alertaSalvo = alertaCaptor.getValue();
        assertEquals(1L, alertaSalvo.getUsuarioId());
        assertEquals(1L, alertaSalvo.getMedicaoId());
    }

    @Test
    @DisplayName("Deve gerar alerta quando SpO2 crítico")
    void deveGerarAlertaQuandoSpo2Critico() {
        MedicaoVital input = new MedicaoVital(1L, 1L, TipoMedicao.MEDICAO_VITAL, 80, 89.0, 120, 80);
        MedicaoVital salva = new MedicaoVital(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_VITAL, 80, 89.0, 120, 80);

        when(medicaoVitalRepository.save(input)).thenReturn(salva);

        useCase.execute(input);

        verify(alertaRepository).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve gerar alerta quando pressão sistólica crítica")
    void deveGerarAlertaQuandoPressaoCritica() {
        MedicaoVital input = new MedicaoVital(1L, 1L, TipoMedicao.MEDICAO_VITAL, 80, 98.0, 200, 80);
        MedicaoVital salva = new MedicaoVital(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_VITAL, 80, 98.0, 200, 80);

        when(medicaoVitalRepository.save(input)).thenReturn(salva);

        useCase.execute(input);

        verify(alertaRepository).save(any(Alerta.class));
    }
}
