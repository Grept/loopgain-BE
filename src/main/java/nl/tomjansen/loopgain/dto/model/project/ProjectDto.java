package nl.tomjansen.loopgain.dto.model.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProjectDto {
    private Long id;
    private String projectName;
    private String director;
    private String producer;

    /*
    * To avoid a stackoverflow error the ProjectDto does not contain a UserDto field. Instead it just holds a String
    * with the username of the associated user. This username can be used to fetch userdata if required.
    * */
    private String projectOwner;
    private List<MediaDto> projectMedia = new ArrayList<>();
}
