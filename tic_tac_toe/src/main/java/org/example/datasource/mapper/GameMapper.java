package org.example.datasource.mapper;

import org.example.datasource.model.GameEntity;
import org.example.domain.model.Game;

public class GameMapper {
    private GameMapper() {}
    public static GameEntity toEntity(Game game) {
        return GameEntity.builder()
                .id(game.getId())
                .board(convertTo1D(game.getBoard()))
                .build();
    }
    public static Game fromEntity(GameEntity entity) {
        return  Game.builder()
                .id(entity.getId())
                .board(convertTo2D(entity.getBoard()))
                .build();
    }

    private static int[][] convertTo2D(int[] board) {
        int size = (int) Math.sqrt(board.length);
        int[][] board2D = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board, i * size, board2D[i], 0, size);
        }
        return board2D;
    }
    private static int[] convertTo1D(int[][] board2D) {
        int size = board2D.length;
        int[] board1D = new int[size * size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(board2D[i], 0, board1D, i * size, size);
        }
        return board1D;
    }
}
