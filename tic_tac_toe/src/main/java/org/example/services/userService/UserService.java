package org.example.services.userService;

import org.example.domain.model.SignUpRequest;

import java.util.UUID;

public interface UserService {
    boolean singUp(SignUpRequest signUpRequest);
    UUID signIn(String login,String password);
}
