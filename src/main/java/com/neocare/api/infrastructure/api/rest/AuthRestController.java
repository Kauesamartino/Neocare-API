package com.neocare.api.infrastructure.api.rest;

import com.neocare.api.interfaces.controller.AuthController;
import com.neocare.api.interfaces.dto.input.LoginRequest;
import com.neocare.api.interfaces.dto.output.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthController authController;

    public AuthRestController(AuthController authController) {
        this.authController = authController;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        final AuthResponse response = authController.login(request);
        return ResponseEntity.ok(response);
    }

}
