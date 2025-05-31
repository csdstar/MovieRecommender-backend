package org.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF 和 CORS（适用于 API 项目）
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)

                // 配置接口权限
                .authorizeHttpRequests(auth -> auth
                        // 允许注册和登录接口无需认证
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/movies/details").permitAll()
                        // 其他所有接口需认证
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}