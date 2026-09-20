package com.project.back_end.controller;
import com.project.back_end.dto.AuthResponse;
import com.project.back_end.dto.LoginDto;
import com.project.back_end.model.Admin;
import com.project.back_end.repository.AdminRepository;
import com.project.back_end.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AdminRepository adminRepository;
    private final TokenService tokenService;
    
    public AuthController(AdminRepository adminRepository, TokenService tokenService) {
        this.adminRepository = adminRepository;
        this.tokenService = tokenService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        // Hardcoded for demo logic based on requirement
        if ("admin".equals(loginDto.getUsername()) && "admin123".equals(loginDto.getPassword())) {
            return ResponseEntity.ok(new AuthResponse(tokenService.generateToken("admin", "ADMIN"), "ADMIN"));
        } else if ("doctor".equals(loginDto.getUsername())) {
            return ResponseEntity.ok(new AuthResponse(tokenService.generateToken("doctor", "DOCTOR"), "DOCTOR"));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
