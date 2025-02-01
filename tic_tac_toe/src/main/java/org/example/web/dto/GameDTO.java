package org.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    @NotNull
    int[][]board;
}
