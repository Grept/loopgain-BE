package nl.tomjansen.loopgaindraft.dto.model.project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgaindraft.model.media.Media;
import nl.tomjansen.loopgaindraft.model.user.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ProjectDto {

    private String projectName;

    private String director;

    private String producer;

    private User projectOwner;

    private List<Media> projectMedia = new ArrayList<>();
}
