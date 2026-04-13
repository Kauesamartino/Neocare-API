package com.neocare.api.infrastructure.api.rest;

import com.neocare.api.application.usecase.dispositivo.ListarDispositivosPorUsuarioUseCase;
import com.neocare.api.application.usecase.dispositivo.LocalizarDispositivoUseCase;
import com.neocare.api.domain.model.Dispositivo;
import com.neocare.api.interfaces.dto.output.DispositivoOutDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoRestController {

    private final LocalizarDispositivoUseCase localizarDispositivoUseCase;
    private final ListarDispositivosPorUsuarioUseCase listarDispositivosPorUsuarioUseCase;

    public DispositivoRestController(LocalizarDispositivoUseCase localizarDispositivoUseCase,
                                     ListarDispositivosPorUsuarioUseCase listarDispositivosPorUsuarioUseCase) {
        this.localizarDispositivoUseCase = localizarDispositivoUseCase;
        this.listarDispositivosPorUsuarioUseCase = listarDispositivosPorUsuarioUseCase;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<DispositivoOutDto> buscarPorId(@PathVariable Long id) {
        Dispositivo dispositivo = localizarDispositivoUseCase.execute(id);
        return ResponseEntity.ok(toOutDto(id, dispositivo));
    }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<DispositivoOutDto>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<DispositivoOutDto> dispositivos = listarDispositivosPorUsuarioUseCase.execute(usuarioId)
                .stream()
                .map(d -> toOutDto(d.getId(), d))
                .toList();
        return ResponseEntity.ok(dispositivos);
    }

    private DispositivoOutDto toOutDto(Long id, Dispositivo dispositivo) {
        return new DispositivoOutDto(
                id,
                dispositivo.getUsuarioId(),
                dispositivo.getTipoDispositivo(),
                dispositivo.getEnderecoDisp(),
                dispositivo.getAtivo()
        );
    }
}
