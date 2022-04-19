package nl.tomjansen.loopgain.dto.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.model.user.UserRoles;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto {

    @NotNull private String username;
    private String password;
    private String emailadress;
    private UserRoles role;
    private List<ProjectDto> projectDtoList = new ArrayList<>();
}
