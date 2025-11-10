package com.neocare.api.infrastructure.api.rest;

import com.neocare.api.interfaces.controller.MedicaoController;
import com.neocare.api.interfaces.dto.input.MedicaoEstresseInDto;
import com.neocare.api.interfaces.dto.input.MedicaoVitalInDto;
import com.neocare.api.interfaces.dto.output.MedicaoEstresseOutDto;
import com.neocare.api.interfaces.dto.output.MedicaoVitalOutDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Adaptador REST para o controller de Medicao.
 * Esta classe contém as anotações específicas do springframework e
 * delega as chamadas para o controller puro.
 */
@RestController
@RequestMapping("/medicoes")
public class MedicaoRestController {

    private final MedicaoController medicaoController;

    public MedicaoRestController(MedicaoController medicaoController) {
        this.medicaoController = medicaoController;
    }

    @PostMapping("/medicao_estresse")
    public ResponseEntity<MedicaoEstresseOutDto> registrarMedicaoEstresse(@RequestBody MedicaoEstresseInDto medicaoEstresseInDto, UriComponentsBuilder uriComponentsBuilder){
        final MedicaoEstresseOutDto medicaoEstresseOutDto = medicaoController.registrarMedicaoEstresse(medicaoEstresseInDto);
        URI uri = uriComponentsBuilder.path("/medicoes/medicao_estresse/{id}").buildAndExpand(medicaoEstresseOutDto.getMedicaoOutDto().getId()).toUri();
        return ResponseEntity.created(uri).body(medicaoEstresseOutDto);
    }

    @PostMapping("/medicao_vital")
    public ResponseEntity<MedicaoVitalOutDto> registrarMedicaoVital(@RequestBody MedicaoVitalInDto medicaoVitalInDto, UriComponentsBuilder uriComponentsBuilder){
        final MedicaoVitalOutDto medicaoVitalOutDto = medicaoController.registrarMedicaoVital(medicaoVitalInDto);
        URI uri = uriComponentsBuilder.path("/medicoes/medicao_vital/{id}").buildAndExpand(medicaoVitalOutDto.medicaoOutDto().getId()).toUri();
        return ResponseEntity.created(uri).body(medicaoVitalOutDto);
    }
}
