package org.example.web.controller;

import lombok.AllArgsConstructor;
import org.example.domain.model.Game;
import org.example.domain.model.Position;
import org.example.domain.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {
    private  GameService gameService;

    @GetMapping("/start")
    public ResponseEntity<int[][]> start() {
        return ResponseEntity.ok(gameService.newGame().getGameBoard().getBoard());
    }
    @PutMapping("move")//{id}/
    public ResponseEntity<int[][]> bestMove(  @RequestBody Position position) { //@PathVariable("id") UUID id
        gameService.setPositionOnBoard(position);
        gameService.getNextMove();
return ResponseEntity.ok(gameService.getGame().getGameBoard().getBoard());
    }
}
