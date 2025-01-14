package org.example.datasource.model;



import lombok.*;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {
    private UUID id;
    private int[][] board;

}
