package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.enums.TipoMedicao;
import com.neocare.api.domain.model.Alerta;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.model.MetricaEstresse;
import com.neocare.api.domain.repository.AlertaRepository;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;
import com.neocare.api.domain.repository.MetricaEstresseRepository;
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
class RegistrarMedicaoEstresseUseCaseTest {

    @Mock
    private MedicaoEstresseRepository medicaoEstresseRepository;

    @Mock
    private AlertaRepository alertaRepository;

    @Mock
    private MetricaEstresseRepository metricaEstresseRepository;

    private RegistrarMedicaoEstresseUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new RegistrarMedicaoEstresseUseCaseImpl(medicaoEstresseRepository, alertaRepository, metricaEstresseRepository);
    }

    @Test
    @DisplayName("Deve salvar medição de estresse e retornar entidade salva")
    void deveSalvarMedicao() {
        MedicaoEstresse input = new MedicaoEstresse(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);
        MedicaoEstresse salva = new MedicaoEstresse(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);

        when(medicaoEstresseRepository.save(input)).thenReturn(salva);
        when(metricaEstresseRepository.save(any(MetricaEstresse.class))).thenAnswer(inv -> inv.getArgument(0));

        MedicaoEstresse resultado = useCase.execute(input);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(medicaoEstresseRepository).save(input);
    }

    @Test
    @DisplayName("Não deve gerar alerta quando valores normais")
    void naoDeveGerarAlertaQuandoValoresNormais() {
        MedicaoEstresse input = new MedicaoEstresse(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);
        MedicaoEstresse salva = new MedicaoEstresse(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);

        when(medicaoEstresseRepository.save(input)).thenReturn(salva);
        when(metricaEstresseRepository.save(any(MetricaEstresse.class))).thenAnswer(inv -> inv.getArgument(0));

        useCase.execute(input);

        verify(alertaRepository, never()).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve gerar alerta quando HRV crítico")
    void deveGerarAlertaQuandoHrvCritico() {
        MedicaoEstresse input = new MedicaoEstresse(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, 10.0, 5.0);
        MedicaoEstresse salva = new MedicaoEstresse(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_ESTRESSE, 10.0, 5.0);

        when(medicaoEstresseRepository.save(input)).thenReturn(salva);
        when(metricaEstresseRepository.save(any(MetricaEstresse.class))).thenAnswer(inv -> inv.getArgument(0));

        useCase.execute(input);

        ArgumentCaptor<Alerta> alertaCaptor = ArgumentCaptor.forClass(Alerta.class);
        verify(alertaRepository).save(alertaCaptor.capture());

        Alerta alertaSalvo = alertaCaptor.getValue();
        assertEquals(1L, alertaSalvo.getUsuarioId());
        assertEquals(1L, alertaSalvo.getMedicaoId());
    }

    @Test
    @DisplayName("Deve gerar alerta quando GSR crítico")
    void deveGerarAlertaQuandoGsrCritico() {
        MedicaoEstresse input = new MedicaoEstresse(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, 50.0, 16.0);
        MedicaoEstresse salva = new MedicaoEstresse(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_ESTRESSE, 50.0, 16.0);

        when(medicaoEstresseRepository.save(input)).thenReturn(salva);
        when(metricaEstresseRepository.save(any(MetricaEstresse.class))).thenAnswer(inv -> inv.getArgument(0));

        useCase.execute(input);

        verify(alertaRepository).save(any(Alerta.class));
    }

    @Test
    @DisplayName("Deve calcular e salvar métrica de estresse ao registrar medição")
    void deveCalcularESalvarMetrica() {
        MedicaoEstresse input = new MedicaoEstresse(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);
        MedicaoEstresse salva = new MedicaoEstresse(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);

        when(medicaoEstresseRepository.save(input)).thenReturn(salva);
        when(metricaEstresseRepository.save(any(MetricaEstresse.class))).thenAnswer(inv -> inv.getArgument(0));

        useCase.execute(input);

        ArgumentCaptor<MetricaEstresse> metricaCaptor = ArgumentCaptor.forClass(MetricaEstresse.class);
        verify(metricaEstresseRepository).save(metricaCaptor.capture());

        MetricaEstresse metricaSalva = metricaCaptor.getValue();
        assertEquals(1L, metricaSalva.getMedicaoEstresseId());
        assertNotNull(metricaSalva.getIndiceEstresse());
        assertNotNull(metricaSalva.getCategoria());
    }
}
