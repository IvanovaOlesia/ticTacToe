package org.example.domain.service;

import org.example.domain.model.Game;

import java.util.UUID;

public interface GameService {
    void getNextMove(Game game);
    boolean isValidGameBoard(UUID id, Game game);
    int isWin(int [][] board);
//    public Game getGame(UUID id);
    public Game newGame();
    boolean isBoardFull(int[][] board);
}
