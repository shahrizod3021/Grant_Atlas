package it.grantatlas.Repository;

import it.grantatlas.Entity.RoomImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface RoomImgRepository extends JpaRepository<RoomImg, Integer> {
}
