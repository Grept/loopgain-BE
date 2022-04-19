package nl.tomjansen.loopgain.service.user;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new CustomUserPrincipal(userOptional.get());
    }
}
