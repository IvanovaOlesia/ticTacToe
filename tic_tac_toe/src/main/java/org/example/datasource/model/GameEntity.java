package org.example.datasource.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.Arrays;
import java.util.UUID;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Games")
@Data
@Builder
public class GameEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
//    @Lob

//
//    private String boardJson;
//    @Transient

    @Column(columnDefinition = "integer[]")
    private  int[] board;
//    @Transient
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//    @PostLoad
//    private void initBoard() {
//        if (boardJson != null) {
//            try {
//                board = objectMapper.readValue(boardJson, int[][].class);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//public GameEntity(UUID id, int[][] board) {
//    this.id = id;
//    this.board = board;
//    try {
//        boardJson = objectMapper.writeValueAsString(board);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
//    public int[][] getBoard() {
//        if (boardJson != null && board == null) {
//            try {
//                board = objectMapper.readValue(boardJson, int[][].class);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return board;
//    }
//
//    public void setBoard(int[][] board) {
//        this.board = board;
//        try {
//            boardJson = objectMapper.writeValueAsString(board);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
