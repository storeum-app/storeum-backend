package com.storeum.controller;

import com.storeum.auth.CustomUserDetails;
import com.storeum.model.entity.User;
import com.storeum.payload.request.*;
import com.storeum.payload.response.*;
import com.storeum.service.AuthService;
import com.storeum.service.EmailConfirmationService;
import com.storeum.util.Constants;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(Constants.BASE_URL + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final EmailConfirmationService emailConfirmationService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest request) {
        JwtResponse response = authService.auth(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        GenericResponse<User> response = authService.register(request);
        return response.buildResponseEntity();
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmEmail(@RequestParam String token) {
        GenericResponse<String> response = emailConfirmationService.confirmEmail(token);
        return response.buildResponseEntity();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request,
                                                             @AuthenticationPrincipal CustomUserDetails user) {
        MDC.put("userId", String.valueOf(user.getId()));
        RefreshTokenResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@Valid @RequestBody LogoutRequest request,
                                             @AuthenticationPrincipal CustomUserDetails user) {
        MDC.put("userId", String.valueOf(user.getId()));
        String response = authService.logout(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/exhange-oauth")
    public ResponseEntity<JwtResponse> exchangeOAuthToken(@RequestBody OAuthTokenRequest request) {
        JwtResponse response = authService.exchangeOAuthToken(request.getToken());
        return ResponseEntity.ok(response);
    }
}