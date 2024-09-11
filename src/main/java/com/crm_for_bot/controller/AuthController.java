package com.crm_for_bot.controller;

import com.crm_for_bot.entity.JwtRequest;
import com.crm_for_bot.entity.JwtResponse;
import com.crm_for_bot.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        log.info("Attempting login for user: {}", authRequest.getUsername());
        try {
            final JwtResponse token = authService.login(authRequest);
            log.info("Login successful for user: {}", authRequest.getUsername());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            log.error("Login failed for user: {}", authRequest.getUsername(), e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/revoke")
    public ResponseEntity<?> revokeToken(@RequestParam String accessToken) {
        log.info("Attempting to revoke token");
        boolean isRevokeSuccess = authService.revokeToken(accessToken);
        if (isRevokeSuccess) {
            log.info("Token revoked successfully");
            return ResponseEntity.ok("Token was revoked successfully");
        } else {
            log.warn("Token revocation failed");
            return ResponseEntity.badRequest().body("Token was not revoked");
        }
    }
}
