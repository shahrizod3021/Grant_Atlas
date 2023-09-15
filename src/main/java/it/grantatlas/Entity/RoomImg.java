package it.grantatlas.Entity;

import it.grantatlas.Entity.template.AbsNameEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RoomImg  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UUID roomImg;

    public RoomImg(UUID roomImg) {
        this.roomImg = roomImg;
    }
}
