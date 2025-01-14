package org.example.web.mapper;

import org.example.domain.model.Game;
import org.example.web.dto.GameDTO;

public class GameMapperDTO {
    public static GameDTO toDTO(Game game){
        return GameDTO.builder()
                .id(game.getId())
                .board(game.getBoard())
                .build();
    }
    public static Game fromDTO(GameDTO game){
        return Game.builder()
                .id(game.getId())
                .board(game.getBoard())
                .build();
    }
}
