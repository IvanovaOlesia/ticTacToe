package org.example.services.gameService;

public class GameEvaluator {
    private GameEvaluator() {}
    public static int evaluateBoard(int[][] board) {
        int score = isWin(board);
        if (score == 10 || score == -10) {
            return score;
        }
        return 0;
    }

    public static int isWin(int[][]board) {

        int rowResult = checkRows(board);
        if (rowResult != 0) return rowResult;


        int colResult = checkColumns(board);
        if (colResult != 0) return colResult;


        int diagResult = checkDiagonals(board);
        if (diagResult != 0) return diagResult;

        return 0;
    }

    private static int checkRows(int[][] board) {
        for (int row = 0; row < BoardUtils.NUM_ROWS; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == MoveService.COMPUTER) return 10;
                else if (board[row][0] == MoveService.PLAYER) return -10;
            }
        }
        return 0;
    }


    private static int checkColumns(int[][] board) {
        for (int col = 0; col < BoardUtils.NUM_COLUMNS; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == MoveService.COMPUTER) return 10;
                else if (board[0][col] == MoveService.PLAYER) return -10;
            }
        }
        return 0;
    }


    private static int checkDiagonals(int[][] board) {
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == MoveService.COMPUTER) return 10;
            else if (board[0][0] == MoveService.PLAYER) return -10;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == MoveService.COMPUTER) return 10;
            else if (board[0][2] == MoveService.PLAYER) return -10;
        }

        return 0;
    }





}
