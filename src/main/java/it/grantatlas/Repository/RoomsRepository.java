package it.grantatlas.Repository;

import it.grantatlas.Entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
}
