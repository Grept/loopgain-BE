package nl.tomjansen.loopgain.dto.mapper;

import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.Authority;
import nl.tomjansen.loopgain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                .setPassword(user.getPassword())
                .setProjectDtoList(projectDtoList);
    }

    public static User dtoToEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.addAuthority(new Authority(dto.getUsername(), dto.getRole()));

        return user;
    }
}
