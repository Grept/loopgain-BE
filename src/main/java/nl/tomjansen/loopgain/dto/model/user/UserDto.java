package nl.tomjansen.loopgain.dto.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.model.user.UserRoles;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto {
    private String username;
    private String emailadress;
    private UserRoles role;
    private List<ProjectDto> projectDtoList = new ArrayList<>();
}
