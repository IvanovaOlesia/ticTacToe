package org.example.services.gameService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.datasource.mapper.GameMapper;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;
import org.example.domain.model.Position;
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

    public Game newGame() {
        return GameMapper.fromEntity(gameRepository.save(GameMapper.toEntity(new Game())));
    }

    @Override
    public void getNextMove(Game game) {
        Position position = bestMove(game.getBoard());
        if (position.getCol() == -1) return;
        game.getBoard()[position.getRow()][position.getCol()] = -1;
        gameRepository.save(GameMapper.toEntity(game));
    }


    public Position bestMove(int[][] gameBoard) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};
        int[][] board = createGameBoardCopy(gameBoard);
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
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
    public int minimax(int[][] board, boolean isMax) {
        int score = evaluateBoard(board);

        if (score != 0 || isBoardFull(board)) {
            return score;
        }

        return isMax ? getBestMaxMove(board) : getBestMinMove(board);
    }

    private int evaluateBoard(int[][] board) {
        int score = isWin(board);
        if (score == 10 || score == -10) {
            return score;
        }
        return 0;
    }

    private int getBestMaxMove(int[][] board) {
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
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
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = PLAYER;
                    best = Math.min(best, minimax(board, true));
                    board[i][j] = EMPTY;
                }
            }
        }
        return best;
    }

    @Override
    public boolean isValidGameBoard(UUID id, Game game) {
        int numberOfDiscrepancies = 0;
        Game currentGame = GameMapper.fromEntity(gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Game with id " + id + " not found")));
        int[][] currentBoard = currentGame.getBoard();
        int[][] newBoard = game.getBoard();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                if (newBoard[i][j] != EMPTY && newBoard[i][j] != COMPUTER && newBoard[i][j] != PLAYER) {
                    return false;
                }else  if ((currentBoard[i][j] == PLAYER && newBoard[i][j] == COMPUTER) || (currentBoard[i][j] == COMPUTER && newBoard[i][j] == PLAYER)) {
                    return false;
                }else  if (currentBoard[i][j] != newBoard[i][j]) {
                    numberOfDiscrepancies++;
                }
            }
        }
        return numberOfDiscrepancies == 1;
    }


    @Override
    public int isWin(int[][]board) {

        int rowResult = checkRows(board);
        if (rowResult != 0) return rowResult;


        int colResult = checkColumns(board);
        if (colResult != 0) return colResult;


        int diagResult = checkDiagonals(board);
        if (diagResult != 0) return diagResult;

        return 0;
    }

    private int checkRows(int[][] board) {
        for (int row = 0; row < NUM_ROWS; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == COMPUTER) return 10;
                else if (board[row][0] == PLAYER) return -10;
            }
        }
        return 0;
    }


    private int checkColumns(int[][] board) {
        for (int col = 0; col < NUM_COLUMNS; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == COMPUTER) return 10;
                else if (board[0][col] == PLAYER) return -10;
            }
        }
        return 0;
    }


    private int checkDiagonals(int[][] board) {
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




        public boolean isBoardFull ( int[][] board){
            for (int i = 0; i < NUM_ROWS; i++) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    if (board[i][j] == EMPTY) return false;
                }
            }
            return true;
        }

        public int[][] createGameBoardCopy ( int[][] board){
            int[][] boardCopy = new int[NUM_ROWS][NUM_COLUMNS];
            for (int i = 0; i < NUM_ROWS; i++) {
                System.arraycopy(board[i], 0, boardCopy[i], 0, NUM_COLUMNS);

            }
            return boardCopy;
        }

}
