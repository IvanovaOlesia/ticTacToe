package org.example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class Game {
    private UUID id;
    private int[][] board;
    public Game() {
        this.board = new int[3][3];
    }


}
