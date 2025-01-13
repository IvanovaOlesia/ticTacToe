package org.example.web.mapper;

import org.example.domain.model.Game;
import org.example.domain.model.GameBoard;
import org.example.web.dto.GameDTO;

public class GameMapperDTO {
    public static GameDTO toDTO(Game game){
        return GameDTO.builder()
                .id(game.getId())
                .board(game.getGameBoard().getBoard())
                .build();
    }
    public static Game fromDTO(GameDTO game){
        return Game.builder()
                .id(game.getId())
                .gameBoard(new GameBoard(game.getBoard()))
                .build();
    }
}
