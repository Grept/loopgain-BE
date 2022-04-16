package nl.tomjansen.loopgain.dto.mapper;

import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.model.user.User;

public abstract class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto()
                .setUsername(user.getUsername())
                .setEmailadress(user.getEmailadress())
                .setRole(user.getRole());

    }
}
