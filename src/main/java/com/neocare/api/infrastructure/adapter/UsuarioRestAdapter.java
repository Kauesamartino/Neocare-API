package com.neocare.api.infrastructure.adapter;

import com.neocare.api.interfaces.controller.UsuarioController;
import com.neocare.api.interfaces.dto.input.UsuarioInputDTO;
import com.neocare.api.interfaces.dto.output.UsuarioOutputDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioRestAdapter {

    private final UsuarioController usuarioController;

    public UsuarioRestAdapter(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    @PostMapping
    public UsuarioOutputDTO criarUsuario(@RequestBody UsuarioInputDTO usuarioInputDTO) {
        return usuarioController.criarUsuario(usuarioInputDTO);
    }
}
