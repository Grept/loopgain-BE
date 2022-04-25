package nl.tomjansen.loopgain.service.user;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.model.user.Authority;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        User user = userOptional.get();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        for(Authority authority : user.getAuthorities()) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorityList);
    }
}
