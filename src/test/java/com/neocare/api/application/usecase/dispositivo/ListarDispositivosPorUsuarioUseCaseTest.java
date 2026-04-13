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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarDispositivosPorUsuarioUseCaseTest {

    @Mock
    private DispositivoRepository dispositivoRepository;

    private ListarDispositivosPorUsuarioUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListarDispositivosPorUsuarioUseCaseImpl(dispositivoRepository);
    }

    @Test
    @DisplayName("Deve retornar lista de dispositivos do usuário")
    void deveRetornarDispositivosDoUsuario() {
        Long usuarioId = 1L;
        Dispositivo d1 = new Dispositivo(usuarioId, TipoDispositivo.ESP32, "A4:CF:12:45:AE:CC");
        Dispositivo d2 = new Dispositivo(usuarioId, TipoDispositivo.SENSOR_CARDIACO, "B5:CF:22:55:BE:DD");

        when(dispositivoRepository.findByUsuarioId(usuarioId)).thenReturn(List.of(d1, d2));

        List<Dispositivo> resultado = useCase.execute(usuarioId);

        assertEquals(2, resultado.size());
        verify(dispositivoRepository).findByUsuarioId(usuarioId);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando usuário não tem dispositivos")
    void deveRetornarListaVazia() {
        Long usuarioId = 99L;
        when(dispositivoRepository.findByUsuarioId(usuarioId)).thenReturn(Collections.emptyList());

        List<Dispositivo> resultado = useCase.execute(usuarioId);

        assertTrue(resultado.isEmpty());
    }
}
