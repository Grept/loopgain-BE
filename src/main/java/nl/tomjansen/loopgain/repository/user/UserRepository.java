package nl.tomjansen.loopgain.repository.user;

import nl.tomjansen.loopgain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
