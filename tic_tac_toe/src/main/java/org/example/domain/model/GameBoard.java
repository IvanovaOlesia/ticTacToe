package org.example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameBoard {
    private final int[][] board;

    public GameBoard() {
        this.board = new int [3][3];
    }

}
