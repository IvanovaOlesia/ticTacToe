package org.example.services.gameService;

import org.example.domain.model.Position;

public class MinMax {

    public static final int PLAYER = 1;  // Игрок
    public static final int COMPUTER = -1; // Компьютер
    public static final int EMPTY = 0;   // Пустая клетка

    // Метод для получения лучшего хода для компьютера
    public static Position bestMove(int[][] gameBoard) {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1}; // Инициализация хода как недопустимого
        int[][] board = createGameBoardCopy(gameBoard);
        // Проходим по всем клеткам и находим лучший ход
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Если клетка пуста, пробуем сделать ход
                if (board[i][j] == EMPTY) {
                    board[i][j] = COMPUTER; // Делаем ход
                    int moveVal = minimax(board, 0, false); // Оцениваем ход
                    board[i][j] = EMPTY; // Откатываем ход

                    // Обновляем лучший ход
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

    // Минимакс алгоритм для оценки хода
    public static int minimax(int[][] board, int depth, boolean isMax) {
        int score = evaluate(board);

        // Если игрок победил, возвращаем оценку
        if (score == 10) return score;

        // Если компьютер победил, возвращаем оценку
        if (score == -10) return score;

        // Если ничья
        if (isBoardFull(board)) return 0;

        // Если ход компьютера
        if (isMax) {
            int best = Integer.MIN_VALUE;
            // Проходим по всем клеткам
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = COMPUTER;
                        best = Math.max(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        }
        // Если ход игрока
        else {
            int best = Integer.MAX_VALUE;
            // Проходим по всем клеткам
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER;
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = EMPTY;
                    }
                }
            }
            return best;
        }
    }

    // Метод для оценки текущего состояния игры
    public static int evaluate(int[][] board) {
        // Проверяем выигрышные комбинации для игрока и компьютера

        // Проверка для строк и столбцов
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

        // Проверка для диагоналей
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == COMPUTER) return 10;
            else if (board[0][0] == PLAYER) return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == COMPUTER) return 10;
            else if (board[0][2] == PLAYER) return -10;
        }

        // Если нет победителя
        return 0;
    }
    // Метод для проверки, заполнена ли доска
    public static boolean isBoardFull(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) return false;
            }
        }
        return true;
    }

    public static int[][] createGameBoardCopy(int[][] board) {
        int[][] boardCopy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }
        return boardCopy;
    }
}