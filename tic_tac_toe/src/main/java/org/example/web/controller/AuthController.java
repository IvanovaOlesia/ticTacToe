package org.example.web.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.datasource.model.SignUpRequest;
import org.example.services.userService.UserService;
import org.example.services.userService.UserServiceImp;
import org.example.web.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {
    private UserService  userService;
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> register(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.singUp(signUpRequest));
    }
    @GetMapping("signIn")
    public ResponseEntity<UUID> authenticate(@RequestHeader("Authorization") String authHeader){
        if (authHeader == null || authHeader.startsWith("Basic ")){
           throw new RuntimeException("The Authorization header is missing");
        }
        String base64 = new String(Base64.getDecoder().decode(authHeader));
        String[] values = base64.split(":", 2);
        if (values.length < 2) {
            throw new IllegalArgumentException("Incorrect login and password format");
        }
        String login = values[0];
        String password = values[1];
        userService.signIn(login, password);
    }
}
