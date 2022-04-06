package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.model.project.Project;

public class ProjectMapper {

    public static ProjectDto toDto(Project project) {
        return new ProjectDto()
                .setProjectOwner(project.getProjectOwner())
                .setProjectName(project.getProjectName())
                .setProjectMedia(project.getProjectMedia());
    }
}
