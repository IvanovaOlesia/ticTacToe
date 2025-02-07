package org.example.services.authService;

import lombok.RequiredArgsConstructor;
import org.example.datasource.model.UserEntity;
import org.example.services.userService.UserService;
import org.example.web.dto.SignUpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public boolean signUp(SignUpRequest signUpRequest) {
        return userService.create(new UserEntity( signUpRequest.username(),passwordEncoder.encode(signUpRequest.password())) );
    }

    public UUID signIn(String authHeader) {

        String base64 = new String(Base64.getDecoder().decode(authHeader.substring("Basic ".length())));
        String[] values = base64.split(":", 2);

        String login = values[0];
        String password = values[1];
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, password)
            );
        }catch(BadCredentialsException e){
            throw new RuntimeException("Invalid login or password",e);
        }
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Authentication failed");
        }
        return userService.getByUsername(authentication.getName()).getId();
    }
}
