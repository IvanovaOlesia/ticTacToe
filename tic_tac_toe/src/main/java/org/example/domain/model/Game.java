package org.example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class Game {
    private UUID id;
    private int[]board;
    public Game() {
//        this.id = UUID.randomUUID();
        this.board = new int[9];
    }


}
