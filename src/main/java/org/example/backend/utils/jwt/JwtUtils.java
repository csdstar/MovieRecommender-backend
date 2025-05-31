package org.example.backend.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
    // 注入配置文件中的jwt参数
    private final JwtProperties jwtProperties;

    public JwtUtils(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(Integer userId) {
        // 通过获取的secret参数构造key
        final String secret = jwtProperties.getSecret();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtProperties.getExpiration());

        // 使用id构建的key来创建token
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Integer parseToken(String token) {
        // 通过获取的secret参数构造key
        final String secret = jwtProperties.getSecret();
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        // 将token通过同样方式解析回id
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)  // 使用 builder 模式设置签名密钥
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Integer.valueOf(claims.getSubject());
    }
}

