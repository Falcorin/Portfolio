package ru.ex.autorisation.service.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.util.MimeTypeUtils;
import ru.ex.autorisation.service.security.JwtConfig;
import ru.ex.autorisation.service.security.JwtTokenProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private static final String[] WHITE_LIST = {
            "/login",
            "/"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) ->
                        {
                            try {
                                authz.antMatchers(WHITE_LIST)
                                        .permitAll()
                                        .anyRequest()
                                        .hasAuthority("user")
                                        .and()
                                        .exceptionHandling()
                                        .accessDeniedHandler(accessDeniedHandler())
                                        .authenticationEntryPoint(authenticationEntryPoint())
                                        .and()
                                        .apply(new JwtConfig(jwtTokenProvider));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                )
                .httpBasic(withDefaults())
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setStatus(401);
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                response.getWriter().write("{error: " + authException.getMessage() + "}");
                response.getWriter().flush();
            }
        };
    }
}
