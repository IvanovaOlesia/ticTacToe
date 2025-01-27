package org.example.datasource.mapper;

import org.example.datasource.model.GameEntity;
import org.example.domain.model.Game;

public class GameMapper {
    public static GameEntity toEntity(Game game) {
        return new GameEntity(
                game.getId(),
                game.getBoard());
//        return GameEntity.builder()
//                .board(game.getBoard())
//                .id(game.getId())
//                .build();
    }
    public static Game fromEntity(GameEntity entity) {
        return  Game.builder()
                .id(entity.getId())
                .board(entity.getBoard())
                .build();
    }
}
