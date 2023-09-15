package it.grantatlas.Projection;

import it.grantatlas.Entity.Comment;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Comment.class, name = "CustomComment")
public interface CustomComment {

    Integer getId();

    String getName();

    String getMessage();

    boolean isHidden();

}
