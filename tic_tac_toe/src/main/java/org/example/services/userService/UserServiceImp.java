package org.example.services.userService;

import org.example.datasource.model.SignUpRequest;

import java.util.UUID;

public class UserServiceImp implements UserService {
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean singUp(SignUpRequest signUpRequest) {
        return false;
    }

    @Override
    public UUID signIn(SignUpRequest signUpRequest) {
        return null;
    }
}
