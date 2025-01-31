package org.example.services.gameService;

import lombok.AllArgsConstructor;
import org.example.datasource.mapper.GameMapper;
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

    public Game newGame() {
        return GameMapper.fromEntity( gameRepository.save(GameMapper.toEntity(new Game())));
    }

    @Override
    public void getNextMove(Game game) {
        Position position = bestMove(game.getBoard());
        game.getBoard()[3 *position.getRow() + position.getCol()] = -1;

        gameRepository.save(GameMapper.toEntity(game));
    }
    public Position bestMove(int[] gameBoard) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};
        int[] board = createGameBoardCopy(gameBoard);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[3 * i +j] == EMPTY) {
                    board[3 * i +j] = COMPUTER;
                    int moveVal = minimax(board, 0);
                    board[3 * i +j] = EMPTY;

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

    @Override
    public boolean isValidGameBoard(UUID id, Game game) {
        int  numberOfDiscrepancies = 0;
        Game currentGame = GameMapper.fromEntity( gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Game not found")));
        int[] currentBoard = currentGame.getBoard();
        System.out.println("Current board: ");
        System.out.println(Arrays.toString(currentBoard));

        int[] newBoard = game.getBoard();
        System.out.println("New board: ");
        System.out.println(Arrays.toString(currentBoard));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (currentBoard[3 * row + col] != newBoard[3 * row + col]) numberOfDiscrepancies++;
            }
        }
        return numberOfDiscrepancies <= 1;
    }


    @Override
    public int isWin(int [] board) {
        for (int row = 0; row < 3; row++) {
            if (board[3 * row] == board[3 * row + 1] && board[3 * row + 1] == board[3 * row + 2]) {
                if (board[3 *row] == COMPUTER) return 10;
                else if (board[3 *row] == PLAYER) return -10;
            }
            if (board[row] == board[3 + row] && board[3 + row] == board[6 +row]) {
                if (board[row] == COMPUTER) return 10;
                else if (board[row] == PLAYER) return -10;
            }
        }

        if (board[0] == board[4] && board[4] == board[8]) {
            if (board[0] == COMPUTER) return 10;
            else if (board[0] == PLAYER) return -10;
        }
        if (board[2] == board[4] && board[4] == board[6]) {
            if (board[2] == COMPUTER) return 10;
            else if (board[2] == PLAYER) return -10;
        }

        return 0;
    }

//    public Game getGame(UUID id) {
//        return  gameRepository.get(id);
//    }




    public int minimax(int[] board, int depth) {
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
                    if (board[3 * i + j] == EMPTY) {
                        board[3 * i + j] = COMPUTER;
                        best = Math.max(best, minimax(board, depth + 1));
                        board[3 * i + j] = EMPTY;
                    }
                }
            }
            return best;


    }

    public boolean isBoardFull(int[] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[3 * i + j] == EMPTY) return false;
            }
        }
        return true;
    }

    public  int[] createGameBoardCopy(int[] board) {
        int[] boardCopy = new int[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardCopy[3 * i + j] = board[3 * i + j];
            }
        }
        return boardCopy;
    }
}
