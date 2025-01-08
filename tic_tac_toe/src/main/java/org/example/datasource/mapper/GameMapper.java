package org.example.datasource.mapper;

import org.example.datasource.model.GameEntity;
import org.example.domain.model.Game;
import org.example.domain.model.GameBoard;

public class GameMapper {
    public static GameEntity toEntity(Game game) {
        return GameEntity.builder()
                .board(game.getGameBoard().getBoard())
                .id(game.getId())
                .build();
    }
    public static Game fromEntity(GameEntity entity) {
        return  Game.builder()
                .id(entity.getId())
                .gameBoard(new GameBoard(entity.getBoard()))
                .build();
    }
}
