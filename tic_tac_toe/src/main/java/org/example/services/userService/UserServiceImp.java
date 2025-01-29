package org.example.services.userService;

import lombok.AllArgsConstructor;
import org.example.datasource.model.UserEntity;
import org.example.domain.model.SignUpRequest;
import org.example.datasource.repository.UserRepository;
import org.example.web.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    @Override
    public boolean singUp(SignUpRequest signUpRequest) {
        userRepository.save(new UserEntity( signUpRequest.getLogin(),signUpRequest.getPassword() ) );
        return true;
    }

    @Override
    public UUID signIn(String login,String password) {
        return null;
    }
}
