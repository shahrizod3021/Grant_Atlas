package it.grantatlas.Entity;

import it.grantatlas.Entity.template.AbsNameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Rooms extends AbsNameEntity {

    @Column(name = "description_uz", length = 20000)
    private String description;

    @Column(name = "description_ru", length = 20000)
    private String descriptionRu;

    @Column(name = "description_eng", length = 20000)
    private String descriptionEng;

    @Column(name = "description_turk", length = 20000)
    private String descriptionTurk;

    @Column(name = "how_many")
    private String howMany;

    @Column(name = "how_much")
    private String howMuchRoom;

    @Column(name = "size")
    private String size;

    @ManyToMany
    private List<RoomImg> roomImgList;


}
