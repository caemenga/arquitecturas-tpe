package org.app.monopatin.config;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.app.monopatin.security.AuthorityConstant;
import org.app.monopatin.security.JwtFilter;
import org.app.monopatin.services.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HttpService http;
    private final JwtParser jwtParser;

    private Dotenv env = Dotenv.load();

    public HttpConfig() {
        String secret = env.get("SECRET_KEY");
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
                .authorizeRequests()
                .requestMatchers("monopatines").hasAuthority(AuthorityConstant.ADMIN)

                        .anyRequest().authenticated();
        http.anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}