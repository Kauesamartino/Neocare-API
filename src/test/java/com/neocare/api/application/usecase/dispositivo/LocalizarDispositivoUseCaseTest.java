package com.neocare.api.application.usecase.dispositivo;

import com.neocare.api.domain.enums.TipoDispositivo;
import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.domain.repository.DispositivoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalizarDispositivoUseCaseTest {

    @Mock
    private DispositivoRepository dispositivoRepository;

    private LocalizarDispositivoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new LocalizarDispositivoUseCaseImpl(dispositivoRepository);
    }

    @Test
    @DisplayName("Deve retornar dispositivo quando encontrado por ID")
    void deveRetornarDispositivoQuandoEncontrado() {
        Dispositivo dispositivo = new Dispositivo(1L, TipoDispositivo.ESP32, "A4:CF:12:45:AE:CC");
        when(dispositivoRepository.findById(1L)).thenReturn(dispositivo);

        Dispositivo resultado = useCase.execute(1L);

        assertNotNull(resultado);
        assertEquals(TipoDispositivo.ESP32, resultado.getTipoDispositivo());
        verify(dispositivoRepository).findById(1L);
    }
}
