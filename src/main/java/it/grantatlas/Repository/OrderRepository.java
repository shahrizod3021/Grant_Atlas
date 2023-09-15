package it.grantatlas.Repository;

import it.grantatlas.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@CrossOrigin
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findOrderByRoomsId(Integer rooms_id);
}
