package com.crm_for_bot.config;

import com.crm_for_bot.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuration class for setting up Spring Security.
 * Defines authorization policy, request filtering, CORS configuration, and other security aspects.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * Filter for handling JWT requests.
     */
    private final JwtRequestFilter jwtFilter;

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object for configuring HTTP request security.
     * @return SecurityFilterChain - the chain of security filters.
     * @throws Exception can be thrown during HttpSecurity configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disables basic authentication and CSRF protection
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)

                // Enables CORS with default settings
                .cors(Customizer.withDefaults())

                // Configures session policy to be stateless (no sessions will be created)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configures authorization rules for different endpoints
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/api/auth/login", "/api/auth/revoke", "/api/jwt/",
                                        "/api/auth/token", "/swagger-ui.html", "/v3/api-docs/**",
                                        "/swagger-ui/**", "/webjars/swagger-ui/**", "/h2-console/**")
                                .permitAll()  // Open access for these endpoints
                                .requestMatchers("/api/statements/**").hasRole("USER")  // Only accessible to users with USER role
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")  // Only accessible to users with ADMIN role
                                .anyRequest().authenticated()  // All other requests require authentication
                )

                // Adds JWT filter after UsernamePasswordAuthenticationFilter
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                // Configures exception handling for access denial
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedPage("/403")
                );

        return http.build();
    }

    /**
     * Configures CORS settings to allow interaction with the client application on specific URLs.
     *
     * @return UrlBasedCorsConfigurationSource - the source of CORS configuration.
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allowed origins
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));

        // Allowed HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Allowed headers
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Allows credentials (e.g., cookies)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Applies the configuration to all routes
        return source;
    }

    /**
     * Creates a CORS filter based on the configuration.
     *
     * @return CorsFilter - the filter for handling CORS requests.
     */
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}