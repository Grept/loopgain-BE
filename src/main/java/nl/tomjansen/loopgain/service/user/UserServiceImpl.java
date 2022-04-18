package nl.tomjansen.loopgain.service.user;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.mapper.UserMapper;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.exception.RecordNotFoundException;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

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
}
