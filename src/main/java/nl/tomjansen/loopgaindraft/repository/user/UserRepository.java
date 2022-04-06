package nl.tomjansen.loopgaindraft.repository.user;

import nl.tomjansen.loopgaindraft.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
