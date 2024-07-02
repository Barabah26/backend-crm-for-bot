package com.crm_for_bot.service;

import com.crm_for_bot.entity.JwtRequest;
import com.crm_for_bot.entity.JwtResponse;
import com.crm_for_bot.entity.User;
import com.crm_for_bot.exception.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final Map<String, List<String>> accessStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    public List<String> getTokensByUser(String userName) {
        return accessStorage.getOrDefault(userName, new ArrayList<>());
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("User not found"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getEncryptedPassword())) {
            throw new AuthException("Password is incorrect");
        }

        final String accessToken = jwtProvider.generateAccessToken(user);
        final String refreshToken = jwtProvider.generateRefreshToken(user);
        refreshStorage.put(user.getUserName(), refreshToken);
        accessStorage.computeIfAbsent(user.getUserName(), k -> new ArrayList<>()).add(accessToken);

        return new JwtResponse(accessToken, refreshToken);
    }
}
