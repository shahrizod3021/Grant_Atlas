package it.grantatlas.Entity;

import it.grantatlas.Entity.template.AbsNameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class News extends AbsNameEntity {

    @Column(name = "more_description_uz", length = 20000)
    private String descriptionUz;

    @Column(name = "more_description_ru", length = 20000)
    private String descriptionRu;

    @Column(name = "more_description_eng", length = 20000)
    private String descriptionEng;

    @Column(name = "more_description_turk", length = 20000)
    private String descriptionTurk;

    @Column(name = "news_img")
    private UUID img;
}
