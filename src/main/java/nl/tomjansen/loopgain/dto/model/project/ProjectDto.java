package nl.tomjansen.loopgain.dto.model.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.model.user.User;

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
    private User projectOwner;
    private List<MediaDto> projectMedia = new ArrayList<>();
}
