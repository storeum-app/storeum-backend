package com.avdienko.storeum.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
}
