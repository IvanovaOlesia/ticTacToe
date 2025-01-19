package org.example.web.controller;

import lombok.AllArgsConstructor;
import org.example.domain.model.Game;
import org.example.domain.service.GameService;
import org.example.web.dto.GameDTO;
import org.example.web.mapper.GameMapperDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {
    private  GameService gameService;

    @GetMapping("/start")
    public ResponseEntity<GameDTO> start() {
        return new ResponseEntity<>( GameMapperDTO.toDTO(gameService.newGame()), HttpStatus.OK );
    }
    @PutMapping("/move/{id}")
    public ResponseEntity<?> update(@RequestBody GameDTO gameDTO, @PathVariable UUID id) {
        Game game = GameMapperDTO.fromDTO(gameDTO);
        if (gameService.isValidGameBoard(id, game)){
            gameService.getNextMove(game);
            int isWin = gameService.isWin(game.getBoard());
            if (isWin == 10) {
                return new ResponseEntity<>("You win!", HttpStatus.OK);
            } else if (isWin == -10) {
                return new ResponseEntity<>("You lose!", HttpStatus.OK);
            }else if(gameService.isBoardFull(game.getBoard())) {
                return new ResponseEntity<>("Draw!", HttpStatus.OK);
            }else {
                return new ResponseEntity<>(gameDTO, HttpStatus.OK);
            }
        }else{
            return new ResponseEntity<>(gameDTO, HttpStatus.BAD_REQUEST);
        }

    }
}