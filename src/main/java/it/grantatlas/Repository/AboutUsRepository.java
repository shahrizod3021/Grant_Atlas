package it.grantatlas.Repository;

import it.grantatlas.Entity.AboutUs;
import it.grantatlas.Projection.CustomAbout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin
@RepositoryRestResource(path = "about", collectionResourceRel = "list", excerptProjection = CustomAbout.class)
public interface AboutUsRepository extends JpaRepository<AboutUs, Integer> {
}
