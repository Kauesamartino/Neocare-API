package com.neocare.api.application.usecase.medicao.vital;

import com.neocare.api.domain.enums.TipoMedicao;
import com.neocare.api.domain.model.MedicaoVital;
import com.neocare.api.domain.repository.MedicaoVitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarMedicoesVitaisUseCaseTest {

    @Mock
    private MedicaoVitalRepository medicaoVitalRepository;

    private ListarMedicoesVitaisUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListarMedicoesVitaisUseCaseImpl(medicaoVitalRepository);
    }

    @Test
    @DisplayName("Deve retornar lista de medições vitais do usuário")
    void deveRetornarMedicoesDoUsuario() {
        Long usuarioId = 1L;
        List<MedicaoVital> medicoes = List.of(
                new MedicaoVital(1L, usuarioId, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_VITAL, 80, 98.0, 120, 80),
                new MedicaoVital(2L, usuarioId, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_VITAL, 90, 97.0, 130, 85)
        );

        when(medicaoVitalRepository.findByUsuarioId(usuarioId)).thenReturn(medicoes);

        List<MedicaoVital> resultado = useCase.porUsuario(usuarioId);

        assertEquals(2, resultado.size());
        verify(medicaoVitalRepository).findByUsuarioId(usuarioId);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando usuário não tem medições")
    void deveRetornarListaVazia() {
        Long usuarioId = 99L;
        when(medicaoVitalRepository.findByUsuarioId(usuarioId)).thenReturn(Collections.emptyList());

        List<MedicaoVital> resultado = useCase.porUsuario(usuarioId);

        assertTrue(resultado.isEmpty());
    }
}
