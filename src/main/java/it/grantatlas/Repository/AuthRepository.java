package it.grantatlas.Repository;

import it.grantatlas.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
public interface AuthRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByRole(String role);
}
