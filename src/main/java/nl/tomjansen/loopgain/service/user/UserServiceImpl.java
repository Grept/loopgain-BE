package nl.tomjansen.loopgain.service.user;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.mapper.UserMapper;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.exception.RecordNotFoundException;
import nl.tomjansen.loopgain.exception.UsernameAlreadyInUseException;
import nl.tomjansen.loopgain.model.user.Authority;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getUserData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        Optional<User> userOptional = userRepository.findUserByUsername(userDetails.getUsername());

        if(userOptional.isPresent()) {
            return UserMapper.entityToDto(userOptional.get());
        } else {
            throw new RecordNotFoundException(String.format("User with username:'%s' was not found", userDetails.getUsername()));
        }
    }

    @Override
    public String createUser(UserDto userDto) {

        if(!userRepository.existsByUsername(userDto.getUsername())) {
            /*
            * Normally I use a MapperClass to map a DTO to an Entity but in this case it is easier to do the mapping
            * manually beceause we also need a passwordEncoder bean to encode the password.
            */
            User user = new User();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.addAuthority(new Authority(userDto.getUsername(), userDto.getRole()));

            return userRepository.save(user).getUsername();
        } else {
            throw new UsernameAlreadyInUseException(
                    String.format("Username '%s' is already in use.", userDto.getUsername())
            );
        }
    }
}
