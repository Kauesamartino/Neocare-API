package com.neocare.api.infrastructure.api.rest;

import com.neocare.api.application.usecase.alerta.ListarAlertasUseCase;
import com.neocare.api.domain.model.Alerta;
import com.neocare.api.interfaces.dto.output.AlertaOutDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaRestController {

    private final ListarAlertasUseCase listarAlertasUseCase;

    public AlertaRestController(ListarAlertasUseCase listarAlertasUseCase) {
        this.listarAlertasUseCase = listarAlertasUseCase;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AlertaOutDto>> listarTodos() {
        List<AlertaOutDto> alertas = listarAlertasUseCase.todos().stream()
                .map(this::toOutDto)
                .toList();
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<AlertaOutDto>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<AlertaOutDto> alertas = listarAlertasUseCase.porUsuario(usuarioId).stream()
                .map(this::toOutDto)
                .toList();
        return ResponseEntity.ok(alertas);
    }

    private AlertaOutDto toOutDto(Alerta alerta) {
        return new AlertaOutDto(
                alerta.getId(),
                alerta.getUsuarioId(),
                alerta.getMedicaoId(),
                alerta.getTipoAlerta(),
                alerta.getValorDetectado(),
                alerta.getSeveridade(),
                alerta.getMensagem(),
                alerta.getDataNotificacao()
        );
    }
}
