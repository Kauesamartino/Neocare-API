package com.neocare.api.interfaces.dto.output;

import java.util.List;

public record AuthResponse(
        String token,
        String username,
        List<String> roles
) {

    private final static String TYPE = "Bearer";

    public String type() {
        return TYPE;
    }
}
