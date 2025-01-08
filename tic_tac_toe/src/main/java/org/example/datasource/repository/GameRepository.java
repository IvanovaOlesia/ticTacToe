package org.example.datasource.repository;

import org.example.domain.model.Game;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameRepository {
    private ConcurrentHashMap<UUID, Game> storage = new ConcurrentHashMap<>();

    public void save(Game game) {
        storage.put(game.getId(), game);
    }
    public Game get(UUID id) {
        return storage.get(id);
    }
}
