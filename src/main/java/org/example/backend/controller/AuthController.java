package org.example.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.dto.LoginReq;
import org.example.backend.dto.RegisterReq;
import org.example.backend.result.Result;
import org.example.backend.service.IUserService;
import org.example.backend.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public Result<Map<String, String>> register(@RequestBody @Valid RegisterReq req) {
        Integer userId = userService.register(req);
        String token = jwtUtils.generateToken(userId);
        return Result.success(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginReq req) {
        String token = userService.login(req);
        return ResponseEntity.ok(Map.of("token", token));
    }
}