package org.example.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    @NotNull
    int[][]board;
}
