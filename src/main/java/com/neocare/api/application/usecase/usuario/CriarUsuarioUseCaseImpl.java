package com.neocare.api.application.usecase.usuario;

import com.neocare.api.domain.model.Role;
import com.neocare.api.domain.model.Usuario;
import com.neocare.api.domain.repository.RoleRepository;
import com.neocare.api.domain.repository.UsuarioRepository;
import com.neocare.api.application.exception.*;

public final class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final LocalizarUsuarioUseCase localizarUsuarioUseCase;

    public CriarUsuarioUseCaseImpl(LocalizarUsuarioUseCase localizarUsuarioUseCase, UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.localizarUsuarioUseCase = localizarUsuarioUseCase;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Usuario execute(Usuario usuario) {
        try{
            localizarUsuarioUseCase.execute(usuario.getCpf());
        } catch (EntidadeNaoEncontradaException e){
            Role roleUser = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Role padrão não encontrada"));

            usuario.getCredenciais().adicionarRole(roleUser);
            return usuarioRepository.save(usuario);
        }
        throw new UsuarioUnsupportedOperation("Usuario já cadastrado");
    }
}
