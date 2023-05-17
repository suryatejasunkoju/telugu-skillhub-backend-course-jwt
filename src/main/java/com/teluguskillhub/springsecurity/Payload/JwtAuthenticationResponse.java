package com.teluguskillhub.springsecurity.Payload;

import lombok.*;

@Getter
@Setter
@ToString
public class JwtAuthenticationResponse {
    private String token;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }
}
