package org.example.services.userService;

import lombok.AllArgsConstructor;
import org.example.datasource.model.UserEntity;
import org.example.web.dto.SignUpRequest;
import org.example.datasource.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public boolean create(UserEntity user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }
        save(user);
        return true;
    }
    @Override
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

}
