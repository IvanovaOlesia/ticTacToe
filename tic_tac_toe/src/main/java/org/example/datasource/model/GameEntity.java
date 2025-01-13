package org.example.datasource.model;



import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GameEntity {
    private UUID id;
    private int[][] board;

}
