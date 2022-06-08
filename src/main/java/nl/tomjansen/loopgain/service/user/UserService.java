package nl.tomjansen.loopgain.service.user;

import nl.tomjansen.loopgain.dto.model.user.UserDto;

public interface UserService {
    UserDto getUserData();
    String createUser(UserDto userDto);
}
