package com.neocare.api.domain.repository;

import com.neocare.api.domain.model.Usuario;

import java.util.List;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);

    Usuario findByCpf(String cpf);

    List<Usuario> findAllByAtivoTrue();
}
