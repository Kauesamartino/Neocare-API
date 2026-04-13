package com.neocare.api.domain.model;

import com.neocare.api.domain.enums.TipoDispositivo;
import com.neocare.api.domain.exception.ValidacaoDominioException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispositivoTest {

    @Test
    @DisplayName("Deve criar dispositivo com dados válidos")
    void deveCriarDispositivoValido() {
        Dispositivo dispositivo = new Dispositivo(1L, TipoDispositivo.ESP32, "A4:CF:12:45:AE:CC");

        assertEquals(1L, dispositivo.getUsuarioId());
        assertEquals(TipoDispositivo.ESP32, dispositivo.getTipoDispositivo());
        assertEquals("A4:CF:12:45:AE:CC", dispositivo.getEnderecoDisp());
        assertTrue(dispositivo.getAtivo());
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuarioId é nulo")
    void deveLancarExcecaoQuandoUsuarioIdNulo() {
        assertThrows(ValidacaoDominioException.class, () ->
                new Dispositivo(null, TipoDispositivo.ESP32, "A4:CF:12:45:AE:CC"));
    }

    @Test
    @DisplayName("Deve lançar exceção quando enderecoDisp é vazio")
    void deveLancarExcecaoQuandoEnderecoVazio() {
        assertThrows(ValidacaoDominioException.class, () ->
                new Dispositivo(1L, TipoDispositivo.ESP32, ""));
    }

    @Test
    @DisplayName("Deve lançar exceção quando enderecoDisp é nulo")
    void deveLancarExcecaoQuandoEnderecoNulo() {
        assertThrows(ValidacaoDominioException.class, () ->
                new Dispositivo(1L, TipoDispositivo.ESP32, null));
    }

    @Test
    @DisplayName("Deve definir ID corretamente com setId")
    void deveDefinirIdCorretamente() {
        Dispositivo dispositivo = new Dispositivo(1L, TipoDispositivo.ESP32, "A4:CF:12:45:AE:CC");
        dispositivo.setId(42L);
        assertEquals(42L, dispositivo.getId());
    }

    @Test
    @DisplayName("Deve criar dispositivo com ativo = true por padrão")
    void deveSerAtivoPorPadrao() {
        Dispositivo dispositivo = new Dispositivo(1L, TipoDispositivo.STM32, "B5:CF:22:55:BE:DD");
        assertTrue(dispositivo.getAtivo());
    }

    @Test
    @DisplayName("Deve criar dispositivo com ativo explícito false")
    void deveCriarComAtivoFalse() {
        Dispositivo dispositivo = new Dispositivo(1L, TipoDispositivo.STM32, "B5:CF:22:55:BE:DD", false);
        assertFalse(dispositivo.getAtivo());
    }
}
