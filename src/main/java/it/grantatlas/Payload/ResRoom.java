package it.grantatlas.Payload;

import it.grantatlas.Entity.Rooms;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResRoom {
    private UUID lastImg;

    private Rooms rooms;

}
