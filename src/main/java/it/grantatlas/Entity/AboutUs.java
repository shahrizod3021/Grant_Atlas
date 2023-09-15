package it.grantatlas.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AboutUs  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description_uz", length = 10000)
    private String descriptionUz;

    @Column(name = "description_ru", length = 10000)
    private String descriptionRu;

    @Column(name = "description_eng", length = 10000)
    private String descriptionEng;

    @Column(name = "description_turk", length = 10000)
    private String descriptionTurk;

    @Column(name = "total_guests_number")
    private Integer totalGuestsNumber;

    @Column(name = "total_room_size")
    private Integer totalRoomSize;

    public AboutUs(String descriptionUz, String descriptionRu, String descriptionEng, String descriptionTurk, Integer totalGuestsNumber, Integer totalRoomSize) {
        this.descriptionUz = descriptionUz;
        this.descriptionRu = descriptionRu;
        this.descriptionEng = descriptionEng;
        this.descriptionTurk = descriptionTurk;
        this.totalGuestsNumber = totalGuestsNumber;
        this.totalRoomSize = totalRoomSize;
    }
}
