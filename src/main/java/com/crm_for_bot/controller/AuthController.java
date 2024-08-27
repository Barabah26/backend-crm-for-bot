package com.crm_for_bot.controller;

import com.crm_for_bot.entity.JwtRequest;
import com.crm_for_bot.entity.JwtResponse;
import com.crm_for_bot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/revoke")
    public ResponseEntity<?> revokeToken(@RequestParam String accessToken) {
        boolean isRevokeSuccess = authService.revokeToken(accessToken);
        if (isRevokeSuccess) {
            return ResponseEntity.ok("Token was revoked successfully");
        }
        return ResponseEntity.badRequest().body("Token was not revoked");
    }
}
