package com.neocare.api.infrastructure.api.rest;

import com.neocare.api.application.usecase.medicao.estresse.ListarMedicoesEstresseUseCase;
import com.neocare.api.application.usecase.medicao.vital.ListarMedicoesVitaisUseCase;
import com.neocare.api.interfaces.controller.MedicaoController;
import com.neocare.api.interfaces.dto.input.MedicaoEstresseInDto;
import com.neocare.api.interfaces.dto.input.MedicaoVitalInDto;
import com.neocare.api.interfaces.dto.output.MedicaoEstresseOutDto;
import com.neocare.api.interfaces.dto.output.MedicaoVitalOutDto;
import com.neocare.api.domain.model.MedicaoEstresse;
import com.neocare.api.domain.model.MedicaoVital;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Adaptador REST para o controller de Medicao.
 * Esta classe contém as anotações específicas do springframework e
 * delega as chamadas para o controller puro.
 */
@RestController
@RequestMapping("/medicoes")
public class MedicaoRestController {

    private final MedicaoController medicaoController;
    private final ListarMedicoesEstresseUseCase listarMedicoesEstresseUseCase;
    private final ListarMedicoesVitaisUseCase listarMedicoesVitaisUseCase;

    public MedicaoRestController(MedicaoController medicaoController,
                                 ListarMedicoesEstresseUseCase listarMedicoesEstresseUseCase,
                                 ListarMedicoesVitaisUseCase listarMedicoesVitaisUseCase) {
        this.medicaoController = medicaoController;
        this.listarMedicoesEstresseUseCase = listarMedicoesEstresseUseCase;
        this.listarMedicoesVitaisUseCase = listarMedicoesVitaisUseCase;
    }

    @PostMapping("/medicao_estresse")
    public ResponseEntity<MedicaoEstresseOutDto> registrarMedicaoEstresse(@Valid @RequestBody MedicaoEstresseInDto medicaoEstresseInDto, UriComponentsBuilder uriComponentsBuilder){
        final MedicaoEstresseOutDto medicaoEstresseOutDto = medicaoController.registrarMedicaoEstresse(medicaoEstresseInDto);
        URI uri = uriComponentsBuilder.path("/medicoes/medicao_estresse/{id}").buildAndExpand(medicaoEstresseOutDto.getMedicaoOutDto().getId()).toUri();
        return ResponseEntity.created(uri).body(medicaoEstresseOutDto);
    }

    @PostMapping("/medicao_vital")
    public ResponseEntity<MedicaoVitalOutDto> registrarMedicaoVital(@Valid @RequestBody MedicaoVitalInDto medicaoVitalInDto, UriComponentsBuilder uriComponentsBuilder){
        final MedicaoVitalOutDto medicaoVitalOutDto = medicaoController.registrarMedicaoVital(medicaoVitalInDto);
        URI uri = uriComponentsBuilder.path("/medicoes/medicao_vital/{id}").buildAndExpand(medicaoVitalOutDto.medicaoOutDto().getId()).toUri();
        return ResponseEntity.created(uri).body(medicaoVitalOutDto);
    }

    @GetMapping("/estresse/usuario/{usuarioId}")
    public ResponseEntity<List<MedicaoEstresse>> listarMedicoesEstresse(@PathVariable Long usuarioId) {
        List<MedicaoEstresse> medicoes = listarMedicoesEstresseUseCase.porUsuario(usuarioId);
        return ResponseEntity.ok(medicoes);
    }

    @GetMapping("/vitais/usuario/{usuarioId}")
    public ResponseEntity<List<MedicaoVital>> listarMedicoesVitais(@PathVariable Long usuarioId) {
        List<MedicaoVital> medicoes = listarMedicoesVitaisUseCase.porUsuario(usuarioId);
        return ResponseEntity.ok(medicoes);
    }
}
