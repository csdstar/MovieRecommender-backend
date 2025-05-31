package org.example.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.dto.LoginReq;
import org.example.backend.dto.RegisterReq;
import org.example.backend.result.Result;
import org.example.backend.service.IUserService;
import org.example.backend.utils.jwt.JwtUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public Result<?> register(@RequestBody @Valid RegisterReq req) {
        userService.register(req);
        return Result.success("注册成功！");
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody @Valid LoginReq req) {
        String token = userService.login(req);
        return Result.success(Map.of("token", token));
    }
}