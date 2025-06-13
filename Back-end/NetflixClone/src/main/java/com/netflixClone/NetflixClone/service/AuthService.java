package com.netflixClone.NetflixClone.service;

import com.netflixClone.NetflixClone.dto.request.AuthRequest;
import com.netflixClone.NetflixClone.dto.request.RegisterRequest;
import com.netflixClone.NetflixClone.dto.response.AuthResponse;
import com.netflixClone.NetflixClone.model.User;
import com.netflixClone.NetflixClone.repository.UserRepository;
import com.netflixClone.NetflixClone.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest req) {
        if (userRepo.findByEmail(req.getEmail()).isPresent())
            throw new RuntimeException("Email ya registrado");
        
        User user = User.builder()
                .email(req.getEmail())
                .password(encoder.encode(req.getPassword()))
                .build();
        userRepo.save(user);

        String token = jwtService.generateToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());
        
        return new AuthResponse(token, refreshToken, user.getEmail());
    }

    public AuthResponse authenticate(AuthRequest req) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );
        
        String token = jwtService.generateToken(req.getEmail());
        String refreshToken = jwtService.generateRefreshToken(req.getEmail());
        
        return new AuthResponse(token, refreshToken, req.getEmail()); // ✅ Incluye el email
    }
}