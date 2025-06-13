package com.netflixClone.NetflixClone.controller;


import com.netflixClone.NetflixClone.dto.request.AuthRequest;
import com.netflixClone.NetflixClone.dto.request.RegisterRequest;
import com.netflixClone.NetflixClone.dto.response.AuthResponse;
import com.netflixClone.NetflixClone.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.authenticate(req));
    }
}
