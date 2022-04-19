package nl.tomjansen.loopgain.service.user;

import nl.tomjansen.loopgain.dto.model.user.UserDto;

public interface UserService {

    public UserDto getUserData();

    Long createUser(UserDto userDto);
}
