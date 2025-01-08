package org.example.domain.service;

import org.example.domain.model.Game;
import org.example.domain.model.Position;

import java.util.UUID;

public interface GameService {
    void getNextMove();
    boolean isValidGameBoard();
    boolean isWin();
    void  setPositionOnBoard(Position position);
    Game  newGame();
    Game getGame();
    public Game getGame(UUID id);
    public void save(Game game);
}
