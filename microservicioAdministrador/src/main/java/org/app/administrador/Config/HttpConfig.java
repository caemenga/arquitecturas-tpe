package org.app.administrador.Config;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.app.administrador.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class HttpConfig {

    private final JwtParser jwtParser;
    private final String secret = "TklDTyBGQUxPUEVSTyBZIExVQ0EgU0UgTEEgUkVDT05UUkEgTUVHQSBBUkNISSBSRSBDT01FIFBPUlFVRSBFUyBQVVRBWk8=";

    public HttpConfig() {
        final var keyBytes = Decoders.BASE64.decode(secret);
        final var key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // AGREGAMOS NUESTRA CONFIG DE JWT.
        http.cors(AbstractHttpConfigurer::disable);
        http
                .addFilterBefore(new JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter.class);
        http
                .csrf(AbstractHttpConfigurer::disable)
                // MANEJAMOS LOS PERMISOS A LOS ENDPOINTS.
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}