package org.example.datasource.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class GameEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Lob
    private String boardJson;
    @Transient
    private  int[][] board;
//    @Builder.Default
    @Transient
    private static final ObjectMapper objectMapper = new ObjectMapper();

public GameEntity(UUID id, int[][] board) {
    this.id = id;
    this.board = board;
    try {
        boardJson = objectMapper.writeValueAsString(board);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public int[][] getBoard() {
        if (boardJson == null) return null;
        try {
            board = objectMapper.readValue(boardJson, int[][].class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
        try {
            boardJson = objectMapper.writeValueAsString(board);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
