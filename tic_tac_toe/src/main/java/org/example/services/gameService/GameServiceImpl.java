package org.example.services.gameService;

import lombok.AllArgsConstructor;
import org.example.datasource.mapper.GameMapper;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    public static final int PLAYER = 1;
    public static final int COMPUTER = -1;
    public static final int EMPTY = 0;
    public static final int NUM_COLUMNS = 3;
    public static final int NUM_ROWS = 3;
    public static final int SIZE_BOARD = 9;
    public static final int CENTER_POINT_INDEX = 4;
    public static final int RIGHTMOST_COLUMN_INDEX = 2;
    private static final int LAST_ROW_FIRST_CELL_INDEX = 6;
    private static final int LAST_CELL_INDEX = 8;

    public Game newGame() {
        return GameMapper.fromEntity(gameRepository.save(GameMapper.toEntity(new Game())));
    }

    @Override
    public void getNextMove(Game game) {
        int position = bestMove(game.getBoard());
        if (position == -1) return;
        game.getBoard()[position] = -1;
        gameRepository.save(GameMapper.toEntity(game));
    }


    public int bestMove(int[] gameBoard) {
        int bestVal = Integer.MIN_VALUE;
        int bestMove = -1;
        int[] board = createGameBoardCopy(gameBoard);
        for (int i = 0; i < SIZE_BOARD; i++) {
                if (board[i] == EMPTY) {
                    board[i] = COMPUTER;
                    int moveVal = minimax(board, false);
                    board[i] = EMPTY;

                    if (moveVal > bestVal) {
                        bestMove = i;
                        bestVal = moveVal;
                    }
                }
        }
        return bestMove;
    }
    public int minimax(int[] board, boolean isMax) {
        int score = evaluateBoard(board);

        if (score != 0 || isBoardFull(board)) {
            return score;
        }

        return isMax ? getBestMaxMove(board) : getBestMinMove(board);
    }

    private int evaluateBoard(int[] board) {
        int score = isWin(board);
        if (score == 10 || score == -10) {
            return score;
        }
        return 0;
    }

    private int getBestMaxMove(int[] board) {
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < SIZE_BOARD; i++) {
            if (board[i] == EMPTY) {
                board[i] = COMPUTER;
                best = Math.max(best, minimax(board, false));
                board[i] = EMPTY;
            }
        }
        return best;
    }

    private int getBestMinMove(int[] board) {
        int best = Integer.MAX_VALUE;
        for (int i = 0; i < SIZE_BOARD; i++) {
            if (board[i] == EMPTY) {
                board[i] = PLAYER;
                best = Math.min(best, minimax(board, true));
                board[i] = EMPTY;
            }
        }
        return best;
    }
//    public int minimax(int[] board,  boolean isMax) {
//        int score = isWin(board);
//
//        if (score == 10) {
//            return score;
//        }
//
//        if (score == -10) {
//            return score;
//        }
//
//        if (isBoardFull(board)) {
//            return 0;
//        }
//
//        if (isMax) {
//            int best = Integer.MIN_VALUE;
//            for (int i = 0; i < SIZE_BOARD; i++) {
//                if (board[i] == EMPTY) {
//                    board[i] = COMPUTER;
//                    best = Math.max(best, minimax(board,  false));
//                    board[i] = EMPTY;
//                }
//            }
//            return best;
//        }
//        else {
//            int best = Integer.MAX_VALUE;
//            for (int i = 0; i < SIZE_BOARD; i++) {
//                if (board[i] == EMPTY) {
//                    board[i] = PLAYER;
//                    best = Math.min(best, minimax(board,  true));
//                    board[i] = EMPTY;
//                }
//            }
//            return best;
//        }
//    }

    @Override
    public boolean isValidGameBoard(UUID id, Game game) {
        int numberOfDiscrepancies = 0;
        Game currentGame = GameMapper.fromEntity(gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Game not found")));
        int[] currentBoard = currentGame.getBoard();
        int[] newBoard = game.getBoard();
        for (int i = 0; i < SIZE_BOARD; i++) {
            if (currentBoard[i] != newBoard[i]) {
                numberOfDiscrepancies++;
            }
        }
        return numberOfDiscrepancies <= 1;
    }


    @Override
    public int isWin(int[] board) {

        int rowResult = checkRows(board);
        if (rowResult != 0) return rowResult;


        int colResult = checkColumns(board);
        if (colResult != 0) return colResult;


        int diagResult = checkDiagonals(board);
        if (diagResult != 0) return diagResult;

        return 0;
    }

    private int checkRows(int[] board) {
        for (int row = 0; row < NUM_ROWS; row++) {
            if (board[NUM_COLUMNS * row] == board[NUM_COLUMNS * row + 1] && board[NUM_COLUMNS * row + 1] == board[NUM_COLUMNS * row + 2]) {
                if (board[NUM_COLUMNS * row] == COMPUTER) return 10;
                else if (board[NUM_COLUMNS * row] == PLAYER) return -10;
            }
        }
        return 0;
    }


    private int checkColumns(int[] board) {
        for (int col = 0; col < NUM_COLUMNS; col++) {
            if (board[col] == board[NUM_COLUMNS + col] && board[NUM_COLUMNS + col] == board[LAST_ROW_FIRST_CELL_INDEX + col]) {
                if (board[col] == COMPUTER) return 10;
                else if (board[col] == PLAYER) return -10;
            }
        }
        return 0;
    }


    private int checkDiagonals(int[] board) {
        if (board[0] == board[CENTER_POINT_INDEX] && board[CENTER_POINT_INDEX] == board[LAST_CELL_INDEX]) {
            if (board[0] == COMPUTER) return 10;
            else if (board[0] == PLAYER) return -10;
        }

        if (board[RIGHTMOST_COLUMN_INDEX] == board[CENTER_POINT_INDEX] && board[CENTER_POINT_INDEX ] == board[LAST_ROW_FIRST_CELL_INDEX]) {
            if (board[RIGHTMOST_COLUMN_INDEX] == COMPUTER) return 10;
            else if (board[RIGHTMOST_COLUMN_INDEX] == PLAYER) return -10;
        }

        return 0;
    }




        public boolean isBoardFull ( int[] board){
            for (int i = 0; i < SIZE_BOARD; i++) {
                if (board[i] == EMPTY) return false;
            }
            return true;
        }

        public int[] createGameBoardCopy ( int[] board){
            int[] boardCopy = new int[SIZE_BOARD];
            System.arraycopy(board, 0, boardCopy, 0, SIZE_BOARD);
            return boardCopy;
        }

}
