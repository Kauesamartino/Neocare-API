package com.neocare.api.application.usecase.medicao.estresse;

import com.neocare.api.domain.enums.TipoMedicao;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.repository.MedicaoEstresseRepository;
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
class ListarMedicoesEstresseUseCaseTest {

    @Mock
    private MedicaoEstresseRepository medicaoEstresseRepository;

    private ListarMedicoesEstresseUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListarMedicoesEstresseUseCaseImpl(medicaoEstresseRepository);
    }

    @Test
    @DisplayName("Deve retornar lista de medições do usuário")
    void deveRetornarMedicoesDoUsuario() {
        Long usuarioId = 1L;
        List<MedicaoEstresse> medicoes = List.of(
                new MedicaoEstresse(1L, usuarioId, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_ESTRESSE, 50.0, 5.0),
                new MedicaoEstresse(2L, usuarioId, 1L, LocalDateTime.now(), TipoMedicao.MEDICAO_ESTRESSE, 30.0, 8.0)
        );

        when(medicaoEstresseRepository.findByUsuarioId(usuarioId)).thenReturn(medicoes);

        List<MedicaoEstresse> resultado = useCase.porUsuario(usuarioId);

        assertEquals(2, resultado.size());
        verify(medicaoEstresseRepository).findByUsuarioId(usuarioId);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando usuário não tem medições")
    void deveRetornarListaVazia() {
        Long usuarioId = 99L;
        when(medicaoEstresseRepository.findByUsuarioId(usuarioId)).thenReturn(Collections.emptyList());

        List<MedicaoEstresse> resultado = useCase.porUsuario(usuarioId);

        assertTrue(resultado.isEmpty());
    }
}
