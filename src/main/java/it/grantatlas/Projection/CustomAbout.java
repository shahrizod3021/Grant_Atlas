package it.grantatlas.Projection;

import it.grantatlas.Entity.AboutUs;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = AboutUs.class)
public interface CustomAbout {

    Integer getId();

    String getDescriptionUz();

    String getDescriptionRu();


    String getDescriptionEng();


    String getDescriptionTurk();

    Integer getTotalGuestsNumber();

    Integer getTotalRoomSize();

}
