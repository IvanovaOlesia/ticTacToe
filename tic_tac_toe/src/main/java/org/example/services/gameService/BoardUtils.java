package org.example.services.gameService;

import org.example.domain.model.Game;

public class BoardUtils {
    public static final int NUM_COLUMNS = 3;
    public static final int NUM_ROWS = 3;
    public static boolean isValidBoard(int[][] currentBoard,int[][] newBoard) {
        int numberOfDiscrepancies = 0;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                int currentCell = currentBoard[i][j];
                int newCell = newBoard[i][j];
                if (newCell != MoveService.EMPTY && newCell != MoveService.COMPUTER && newCell != MoveService.PLAYER) {
                    return false;
                }
                if ((currentCell == MoveService.PLAYER && newCell == MoveService.COMPUTER)
                        || (currentCell == MoveService.COMPUTER && newCell == MoveService.PLAYER)) {
                    return false;
                }
                if (currentCell != newCell) {
                    numberOfDiscrepancies++;
                }
            }
        }
        return numberOfDiscrepancies == 1;
    }

    public static int[][] createGameBoardCopy ( int[][] board){
        int[][] boardCopy = new int[NUM_ROWS][NUM_COLUMNS];
        for (int i = 0; i < NUM_ROWS; i++) {
            System.arraycopy(board[i], 0, boardCopy[i], 0, NUM_COLUMNS);

        }
        return boardCopy;
    }
    public static boolean isBoardFull ( int[][] board){
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                if (board[i][j] == MoveService.EMPTY) return false;
            }
        }
        return true;
    }
}
