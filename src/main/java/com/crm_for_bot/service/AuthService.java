package com.crm_for_bot.service;

import com.crm_for_bot.entity.JwtRequest;
import com.crm_for_bot.entity.JwtResponse;
import com.crm_for_bot.entity.Role;
import com.crm_for_bot.entity.User;
import com.crm_for_bot.exception.AuthException;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserServiceImpl userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public List<String> getTokensByUser(String userName) {
        List<String> accessTokens = jwtService.getAccessStorage().get(userName);
        return accessTokens;
    }

    // Assuming Role class has a method getRoleName()
    public JwtResponse login(@NonNull JwtRequest authRequest) {
        if (authRequest.getUsername() == null) {
            throw new AuthException("Username is null");
        }
        final User user = userService.getByLogin(authRequest.getUsername())
                .orElseThrow(() -> new AuthException("User not found"));

        if (passwordEncoder.matches(authRequest.getPassword(), user.getEncryptedPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            jwtService.getRefreshStorage().put(user.getUserName(), refreshToken);

            List<String> accessTokens = jwtService.getAccessStorage().computeIfAbsent(user.getUserName(), k -> new ArrayList<>());
            accessTokens.add(accessToken);
            jwtService.getAccessStorage().put(user.getUserName(), accessTokens);

            // Extract role from the user object assuming roles are of type Role
            String role = user.getRoles().stream()
                    .map(Role::getName) // Adjust this to match your Role class method
                    .findFirst()
                    .orElse("USER");

            return new JwtResponse(accessToken, refreshToken, role);
        } else {
            throw new AuthException("Password is incorrect");
        }
    }


    public boolean revokeToken(@NonNull String accessToken) {
        if (jwtProvider.validateAccessToken(accessToken)) {
            final Claims claims = jwtProvider.getAccessClaims(accessToken);
            final String login = claims.getSubject();
            List<String> tokens = jwtService.getAccessStorage().get(login);
            if (tokens != null) {
                tokens.remove(accessToken);
                if (tokens.isEmpty()) {
                    jwtService.getAccessStorage().remove(login);
                } else {
                    jwtService.getAccessStorage().put(login, tokens);
                }
                return true;
            }
        }
        return false;
    }
}
