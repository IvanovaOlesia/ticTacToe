package org.example.domain.model;

import java.util.UUID;

public class Game {
    private UUID currentGameId;
    private GameBoard gameBoard;
    public Game() {
        this.currentGameId = UUID.randomUUID();
        this.gameBoard = new GameBoard();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getSeedAtPosition(Position position) {
        return gameBoard.getBoard()[position.getRow()][position.getCol()];
    }
    public void setSeedAtPosition(Position position, int seed) {
        gameBoard.getBoard()[position.getRow()][position.getCol()] = seed;
    }

}
