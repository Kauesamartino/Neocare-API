package com.neocare.api.interfaces.controller;

import com.neocare.api.application.usecase.dispositivo.LocalizarDispositivoUseCase;
import com.neocare.api.application.usecase.medicao.estresse.RegistrarMedicaoEstresseUseCase;
import com.neocare.api.application.usecase.medicao.vital.RegistrarMedicaoVitalUseCase;
import com.neocare.api.application.usecase.usuario.LocalizarUsuarioPorIdUseCase;
import com.neocare.api.domain.enums.TipoDispositivo;
import com.neocare.api.domain.enums.TipoMedicao;
import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.model.MedicaoVital;
import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.enums.Sexo;
import com.neocare.api.domain.model.Endereco;
import com.neocare.api.interfaces.dto.input.MedicaoEstresseInDto;
import com.neocare.api.interfaces.dto.input.MedicaoVitalInDto;
import com.neocare.api.interfaces.dto.output.MedicaoEstresseOutDto;
import com.neocare.api.interfaces.dto.output.MedicaoVitalOutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicaoControllerImplTest {

    @Mock
    private RegistrarMedicaoEstresseUseCase registrarMedicaoEstresse;

    @Mock
    private LocalizarDispositivoUseCase localizarDispositivoUseCase;

    @Mock
    private LocalizarUsuarioPorIdUseCase localizarUsuarioPorIdUseCase;

    @Mock
    private RegistrarMedicaoVitalUseCase registrarMedicaoVitalUseCase;

    private MedicaoControllerImpl controller;

    private Usuario usuario;
    private Dispositivo dispositivo;

    @BeforeEach
    void setUp() {
        controller = new MedicaoControllerImpl(
                registrarMedicaoEstresse, localizarDispositivoUseCase,
                localizarUsuarioPorIdUseCase, registrarMedicaoVitalUseCase
        );
        Endereco endereco = new Endereco("Rua Teste", "Bairro", "01234-567", "123", "Apto 1", "SP", "SP");
        usuario = new Usuario(1L, "João", "Silva", "52857264844", "joao@test.com",
                "11999999999", LocalDate.of(1990, 1, 1), Sexo.MASCULINO, 175, 80.0, endereco, true);
        dispositivo = new Dispositivo(1L, TipoDispositivo.ESP32, "A4:CF:12:45:AE:CC");
    }

    @Test
    @DisplayName("Deve registrar medição de estresse com orquestração correta")
    void deveRegistrarMedicaoEstresse() {
        MedicaoEstresseInDto inDto = new MedicaoEstresseInDto(1L, 1L, TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);
        MedicaoEstresse salva = new MedicaoEstresse(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0);

        when(registrarMedicaoEstresse.execute(any(MedicaoEstresse.class))).thenReturn(salva);
        when(localizarUsuarioPorIdUseCase.execute(1L)).thenReturn(usuario);
        when(localizarDispositivoUseCase.execute(1L)).thenReturn(dispositivo);

        MedicaoEstresseOutDto resultado = controller.registrarMedicaoEstresse(inDto);

        assertNotNull(resultado);
        assertEquals(50.0, resultado.getVariacaoFrequenciaCardiaca());
        assertEquals(5.0, resultado.getCondutividadePele());
        assertNotNull(resultado.getMedicaoOutDto());
        assertEquals("João", resultado.getMedicaoOutDto().getNomeUsuario());

        verify(registrarMedicaoEstresse).execute(any(MedicaoEstresse.class));
        verify(localizarUsuarioPorIdUseCase).execute(1L);
        verify(localizarDispositivoUseCase).execute(1L);
    }

    @Test
    @DisplayName("Deve registrar medição vital com orquestração correta")
    void deveRegistrarMedicaoVital() {
        MedicaoVitalInDto inDto = new MedicaoVitalInDto(1L, 1L, TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80);
        MedicaoVital salva = new MedicaoVital(1L, 1L, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80);

        when(registrarMedicaoVitalUseCase.execute(any(MedicaoVital.class))).thenReturn(salva);
        when(localizarUsuarioPorIdUseCase.execute(1L)).thenReturn(usuario);
        when(localizarDispositivoUseCase.execute(1L)).thenReturn(dispositivo);

        MedicaoVitalOutDto resultado = controller.registrarMedicaoVital(inDto);

        assertNotNull(resultado);
        assertEquals(80, resultado.batimentosPorMinuto());
        assertEquals(98.0, resultado.oxigenacaoSangue());
        assertEquals(120, resultado.pressaoSistolica());
        assertEquals(80, resultado.pressaoDiastolica());
        assertNotNull(resultado.medicaoOutDto());

        verify(registrarMedicaoVitalUseCase).execute(any(MedicaoVital.class));
        verify(localizarUsuarioPorIdUseCase).execute(1L);
        verify(localizarDispositivoUseCase).execute(1L);
    }
}
