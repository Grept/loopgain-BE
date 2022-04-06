package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.dto.model.user.UserDto;
import nl.tomjansen.loopgaindraft.model.user.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return new UserDto()
                .setUsername(user.getUsername())
                .setEmailadress(user.getEmailadress())
                .setRole(user.getRole());

    }
}
