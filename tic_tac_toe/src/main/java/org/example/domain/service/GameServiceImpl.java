package org.example.domain.service;

import lombok.AllArgsConstructor;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;
import org.example.domain.model.Position;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    public  final int PLAYER = 1;
    public  final int COMPUTER = -1;
    public final int EMPTY = 0;

    @Override
    public void getNextMove(Game game) {
        Position position = bestMove(game.getBoard());
        game.getBoard()[position.getRow()][position.getCol()] = -1;
        gameRepository.update(game);
    }

    @Override
    public boolean isValidGameBoard(UUID id, Game game) {
        int  numberOfDiscrepancies = 0;
        Game currentGame = gameRepository.get(id);
        int[][] currentBoard = currentGame.getBoard();
        int[][] newBoard = game.getBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (currentBoard[row][col] != newBoard[row][col]) numberOfDiscrepancies++;
            }
        }
        return numberOfDiscrepancies <= 1;
    }


    @Override
    public int isWin(int [][] board) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == COMPUTER) return 10;
                else if (board[row][0] == PLAYER) return -10;
            }
            if (board[0][row] == board[1][row] && board[1][row] == board[2][row]) {
                if (board[0][row] == COMPUTER) return 10;
                else if (board[0][row] == PLAYER) return -10;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == COMPUTER) return 10;
            else if (board[0][0] == PLAYER) return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == COMPUTER) return 10;
            else if (board[0][2] == PLAYER) return -10;
        }

        return 0;
    }

    public Game getGame(UUID id) {
        return  gameRepository.get(id);
    }
    public Game newGame() {
        Game game = new Game();
        gameRepository.save(game);
        return game;
    }

    public Position bestMove(int[][] gameBoard) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};
        int[][] board = createGameBoardCopy(gameBoard);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = COMPUTER;
                    int moveVal = minimax(board, 0);
                    board[i][j] = EMPTY;

                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return new Position(bestMove[0],bestMove[1] );
    }

    public int minimax(int[][] board, int depth) {
        int score = isWin(board);

        if (score == 10) {
            return score;
        }

        if (score == -10) {
            return score;
        }

        if (isBoardFull(board)){
            return 0;
        }


            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = COMPUTER;
                        best = Math.max(best, minimax(board, depth + 1));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;


    }

//    public static int evaluate(int[][] board) {
//
//        for (int row = 0; row < 3; row++) {
//            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
//                if (board[row][0] == COMPUTER) return 10;
//                else if (board[row][0] == PLAYER) return -10;
//            }
//            if (board[0][row] == board[1][row] && board[1][row] == board[2][row]) {
//                if (board[0][row] == COMPUTER) return 10;
//                else if (board[0][row] == PLAYER) return -10;
//            }
//        }
//
//        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
//            if (board[0][0] == COMPUTER) return 10;
//            else if (board[0][0] == PLAYER) return -10;
//        }
//        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
//            if (board[0][2] == COMPUTER) return 10;
//            else if (board[0][2] == PLAYER) return -10;
//        }
//
//        return 0;
//    }
    // Метод для проверки, заполнена ли доска
    public boolean isBoardFull(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    public  int[][] createGameBoardCopy(int[][] board) {
        int[][] boardCopy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    }
}
