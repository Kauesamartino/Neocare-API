package com.neocare.api.infrastructure.api.rest;

import com.neocare.api.interfaces.controller.UsuarioController;
import com.neocare.api.interfaces.dto.input.UsuarioAtualizacaoInputDTO;
import com.neocare.api.interfaces.dto.input.UsuarioInputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioResumoOutputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Adaptador REST para o controller de Usuario.
 * Esta classe contém as anotações específicas do springframework e
 * delega as chamadas para o controller puro.
 */
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
        URI uri = uriComponentsBuilder.path("/usuarios/{cpf}").buildAndExpand(usuarioOutputDTO.getCpf()).toUri();
        return ResponseEntity.created(uri).body(usuarioOutputDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResumoOutputDTO>> listarUsuarios(){

        List<UsuarioResumoOutputDTO> usuarioPage = this.usuarioController.listarUsuarios();
        return ResponseEntity.ok(usuarioPage);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UsuarioOutputDTO> buscarUsuarioPorCpf(@PathVariable String cpf){
        final UsuarioOutputDTO usuarioOutputDTO = this.usuarioController.localizarUsuarioPorCpf(cpf);
        return ResponseEntity.ok(usuarioOutputDTO);
    }

    @PutMapping
    public ResponseEntity<UsuarioOutputDTO> editarUsuario(@RequestBody UsuarioAtualizacaoInputDTO usuarioInputDTO) {
        UsuarioOutputDTO usuarioOutputDTO = this.usuarioController.editarUsuario(usuarioInputDTO);
        return ResponseEntity.ok(usuarioOutputDTO);
    }
}
