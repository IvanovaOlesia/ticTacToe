package org.example.datasource.repository;

import org.example.datasource.model.UserEntity;
import org.example.domain.model.SignUpRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
