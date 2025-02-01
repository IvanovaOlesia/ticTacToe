package org.example.services.userService;

import lombok.AllArgsConstructor;
import org.example.datasource.model.UserEntity;
import org.example.web.dto.SignUpRequest;
import org.example.datasource.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public boolean singUp(SignUpRequest signUpRequest) {
        if (userRepository.findByLogin(signUpRequest.getLogin()).isPresent()) {
            return false;
        }
        userRepository.save(new UserEntity( signUpRequest.getLogin(),passwordEncoder.encode(signUpRequest.getPassword())) );
        return true;
    }

    @Override
    public UUID signIn(String login,String password) {
        return userRepository.findByLogin(login)
                .map(UserEntity::getId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
