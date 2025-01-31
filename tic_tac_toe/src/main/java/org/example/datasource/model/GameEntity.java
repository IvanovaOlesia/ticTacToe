package org.example.datasource.model;



import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;


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
    @Column(columnDefinition = "integer[]")
    private  int[] board;

}
