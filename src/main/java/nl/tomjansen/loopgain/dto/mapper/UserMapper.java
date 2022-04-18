package nl.tomjansen.loopgain.dto.mapper;

import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.User;

import java.util.ArrayList;
import java.util.List;

public abstract class UserMapper {

    public static UserDto entityToDto(User user) {
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project p :  user.getProjectList()){
            projectDtoList.add(ProjectMapper.entityToDto(p));
        }

        return new UserDto()
                .setUsername(user.getUsername())
                .setEmailadress(user.getEmailadress())
                .setRole(user.getRole())
                .setProjectDtoList(projectDtoList);
    }
}
