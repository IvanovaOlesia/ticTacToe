package org.example.datasource.model;



import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;


import java.util.UUID;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name="Games")
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GameEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    @Column(columnDefinition = "integer[]")
    private  int[] board;

}
