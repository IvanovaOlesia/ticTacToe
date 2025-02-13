package org.example.web.mapper;

import org.example.domain.model.Game;
import org.example.web.dto.GameDTO;

public class GameMapperDTO {
    private GameMapperDTO(){}
    public static GameDTO toDTO(Game game){
        return GameDTO.builder()
                .board(game.getBoard())
                .build();
    }
    public static Game fromDTO(GameDTO game){
        return Game.builder()
                .board(game.getBoard())
                .build();
    }


}
