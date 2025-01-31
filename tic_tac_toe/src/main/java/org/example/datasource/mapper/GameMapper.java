package org.example.datasource.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.datasource.model.GameEntity;
import org.example.domain.model.Game;

public class GameMapper {
    private GameMapper() {}
    public static GameEntity toEntity(Game game) {
        return GameEntity.builder()
                .id(game.getId())
                .board(game.getBoard())
                .build();
    }
    public static Game fromEntity(GameEntity entity) {
        return  Game.builder()
                .id(entity.getId())
                .board(entity.getBoard())
                .build();
    }
}
