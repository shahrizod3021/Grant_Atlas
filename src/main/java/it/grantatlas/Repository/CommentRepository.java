package it.grantatlas.Repository;

import it.grantatlas.Entity.Comment;
import it.grantatlas.Projection.CustomComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "comment", excerptProjection = CustomComment.class, collectionResourceRel = "list")
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
