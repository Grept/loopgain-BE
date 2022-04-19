package nl.tomjansen.loopgain.repository.user;

import nl.tomjansen.loopgain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findUserByUsername(String username);
    public Boolean existsByUsername(String username);
}
