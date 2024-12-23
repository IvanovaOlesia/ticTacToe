package org.example.domain.model;

import lombok.Getter;

@Getter
public class GameBoard {
    private final int[][] board;

    public GameBoard() {
        this.board = new int [3][3];
    }

}
