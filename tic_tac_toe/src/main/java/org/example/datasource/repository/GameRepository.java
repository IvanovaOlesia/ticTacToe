package org.example.datasource.repository;

import org.example.datasource.mapper.GameMapper;
import org.example.datasource.model.GameEntity;
import org.example.domain.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {

}
