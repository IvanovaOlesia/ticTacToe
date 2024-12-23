package org.example.domain.service;

import org.example.domain.model.Game;
import org.example.domain.model.Position;

public interface GameService {
    void getNextMove();
    boolean isValidMove();
    boolean isWin();
    void  setPositionOnBoard(Position position);
    Game  newGame();
    Game getGame();
}
