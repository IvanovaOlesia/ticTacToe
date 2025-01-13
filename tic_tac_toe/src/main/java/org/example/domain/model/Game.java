package org.example.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private UUID id;
    private GameBoard gameBoard;
    public Game() {
        this.id = UUID.randomUUID();
        this.gameBoard = new GameBoard();
    }
//
//    public GameBoard getGameBoard() {
//        return gameBoard;
//    }
//
//    public int getSeedAtPosition(Position position) {
//        return gameBoard.getBoard()[position.getRow()][position.getCol()];
//    }
//    public void setSeedAtPosition(Position position, int seed) {
//        gameBoard.getBoard()[position.getRow()][position.getCol()] = seed;
//    }

}
