package org.example.domain.service;

import lombok.AllArgsConstructor;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;
import org.example.domain.model.Position;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    public static final int PLAYER = 1;
    public static final int COMPUTER = -1;
    private Game game;
    @Override
    public void getNextMove() {
        Position position = MinMax.bestMove(game.getGameBoard().getBoard());
        game.getGameBoard().getBoard()[position.getRow()][position.getCol()] = -1;
    }

    @Override
    public boolean isValidGameBoard() {
        return false;
    }


    @Override
    public boolean isWin() {
        int [][] board = game.getGameBoard().getBoard();
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == COMPUTER || board[row][0] == PLAYER) return true;
            }
            if (board[0][row] == board[1][row] && board[1][row] == board[2][row]) {
                if (board[0][row] == COMPUTER || board[0][row] == PLAYER) return true;
            }
        }

        // Проверка для диагоналей
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == COMPUTER || board[0][0] == PLAYER) return true;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == COMPUTER || board[0][2] == PLAYER) return true;
        }
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
    public Game getGame(UUID id) {
        return gameRepository.get(id);
    }
    public void save(Game game) {
        gameRepository.save(game);
    }
}
