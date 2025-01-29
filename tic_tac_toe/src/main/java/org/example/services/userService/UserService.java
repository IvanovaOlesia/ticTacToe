package org.example.services.userService;

import org.example.datasource.model.SignUpRequest;

import java.util.UUID;

public interface UserService {
    boolean singUp(SignUpRequest signUpRequest);
    UUID signIn(String login,String password);
}
