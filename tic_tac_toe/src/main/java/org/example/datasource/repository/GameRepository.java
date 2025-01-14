package org.example.datasource.repository;

import org.example.datasource.mapper.GameMapper;
import org.example.datasource.model.GameEntity;
import org.example.domain.model.Game;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {
    private ConcurrentHashMap<UUID, GameEntity> storage = new ConcurrentHashMap<>();

    public void save(Game game) {
        storage.put(game.getId(), GameMapper.toEntity(game));
    }
    public Game get(UUID id) {
        return GameMapper.fromEntity(storage.get(id));
    }
    public void update(Game game){
        storage.computeIfPresent(game.getId(), (uuid, entity) -> {
            entity.setBoard(game.getBoard());
            return entity;
        });
    }
}
