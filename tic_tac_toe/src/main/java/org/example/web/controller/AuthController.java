package org.example.web.controller;

import lombok.AllArgsConstructor;
import org.example.web.dto.SignUpRequest;
import org.example.services.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService  userService;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> register(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.singUp(signUpRequest));
    }
    @GetMapping("/signIn")
    public ResponseEntity<UUID> authenticate(@RequestHeader("Authorization") String authHeader){

        String base64 = new String(Base64.getDecoder().decode(authHeader.substring(6)));
        String[] values = base64.split(":", 2);

        String login = values[0];
        String password = values[1];
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Invalid login or password");
        }
       return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(login, password));
    }
}
