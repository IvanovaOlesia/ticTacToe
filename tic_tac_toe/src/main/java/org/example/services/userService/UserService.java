package org.example.services.userService;

import org.example.datasource.model.UserEntity;

import java.util.UUID;

public interface UserService {
    UserEntity save(UserEntity user);
    boolean create(UserEntity user);
    UserEntity getByUsername(String username);
}
