package net.sarvesh.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sarvesh.userservice.dto.AuthResponse;
import net.sarvesh.userservice.dto.LoginRequest;
import net.sarvesh.userservice.dto.RegisterRequest;
import net.sarvesh.userservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return userService.login(request);
    }
}