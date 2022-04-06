package nl.tomjansen.loopgaindraft.dto.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgaindraft.model.user.UserRoles;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto {
    private String username;
    private String emailadress;
    private UserRoles role;
}
