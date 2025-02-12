package org.example.services.gameService;

import org.example.domain.model.Position;
import org.springframework.stereotype.Service;

@Service
public class MoveService {
    public static final int PLAYER = 1;
    public static final int COMPUTER = -1;
    public static final int EMPTY = 0;


    public Position bestMove(int[][] gameBoard) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};
        int[][] board = BoardUtils.createGameBoardCopy(gameBoard);
        for (int i = 0; i < BoardUtils.NUM_ROWS; i++) {
            for (int j = 0; j < BoardUtils.NUM_COLUMNS; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = COMPUTER;
                    int moveVal = minimax(board, false);
                    board[i][j] = EMPTY;
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return new Position(bestMove[0], bestMove[1]);
    }
    private int minimax(int[][] board, boolean isMax) {
        int score = GameEvaluator.evaluateBoard(board);

        if (score != 0 || BoardUtils.isBoardFull(board)) {
            return score;
        }

        return isMax ? getBestMaxMove(board) : getBestMinMove(board);
    }




    private int getBestMaxMove(int[][] board) {
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < BoardUtils.NUM_ROWS; i++) {
            for (int j = 0; j < BoardUtils.NUM_COLUMNS; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = COMPUTER;
                    best = Math.max(best, minimax(board, false));
                    board[i][j] = EMPTY;
                }
            }
        }
        return best;
    }

    private int getBestMinMove(int[][] board) {
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < BoardUtils.NUM_ROWS; i++) {
            for (int j = 0; j < BoardUtils.NUM_COLUMNS; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = PLAYER;
                    best = Math.min(best, minimax(board, true));
                    board[i][j] = EMPTY;
                }
            }
        }
        return best;
    }


}
