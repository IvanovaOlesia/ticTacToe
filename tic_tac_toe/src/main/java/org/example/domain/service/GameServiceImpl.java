package org.example.domain.service;

import org.example.domain.model.Game;
import org.example.domain.model.Position;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService{
    private Game game;
    @Override
    public void getNextMove() {
        Position position = MinMax.bestMove(game.getGameBoard().getBoard());
        game.getGameBoard().getBoard()[position.getRow()][position.getCol()] = -1;
    }

    @Override
    public boolean isValidMove() {
        return false;
    }

    @Override
    public boolean isWin() {
        return false;
    }
    @Override
    public void  setPositionOnBoard(Position position) {
        game.getGameBoard().getBoard()[position.getRow()][position.getCol()] = 1;

    }
    @Override
    public Game newGame() {
        game = new Game();
    return game;
    }
    @Override
    public Game getGame() {
        return game;
    }
}
