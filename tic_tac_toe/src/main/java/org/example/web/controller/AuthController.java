package org.example.web.controller;

import lombok.AllArgsConstructor;
import org.example.services.authService.AuthService;
import org.example.web.dto.SignUpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> register(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(signUpRequest));
    }
    @GetMapping("/signIn")
    public ResponseEntity<UUID> authenticate(@RequestHeader("Authorization") String authHeader){
       return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(authHeader));
    }
}
