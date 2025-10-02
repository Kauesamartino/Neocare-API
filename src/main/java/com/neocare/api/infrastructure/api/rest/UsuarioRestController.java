package com.neocare.api.infrastructure.api.rest;

import com.neocare.api.interfaces.controller.UsuarioController;
import com.neocare.api.interfaces.dto.input.UsuarioInputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {

    private final UsuarioController usuarioController;

    public UsuarioRestController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    @PostMapping
    public ResponseEntity<UsuarioOutputDTO> criarUsuario(@RequestBody UsuarioInputDTO usuarioInputDTO, UriComponentsBuilder uriComponentsBuilder) {
        final UsuarioOutputDTO usuarioOutputDTO = usuarioController.criarUsuario(usuarioInputDTO);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioOutputDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioOutputDTO);
    }
}
