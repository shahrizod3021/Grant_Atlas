package it.grantatlas.Repository;

import it.grantatlas.Entity.Request;
import org.hibernate.validator.internal.engine.resolver.JPATraversableResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface RequestRepository extends JpaRepository<Request, Long> {
}
