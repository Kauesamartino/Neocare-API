package com.neocare.api.interfaces.controller;

import com.neocare.api.interfaces.dto.input.LoginRequest;
import com.neocare.api.interfaces.dto.output.AuthResponse;

public interface AuthController {

    /**
     * Realiza o login do usuário
     *
     * @param request Dados de login
     * @return Resposta de autenticação
     */
    AuthResponse login(LoginRequest request);
}
