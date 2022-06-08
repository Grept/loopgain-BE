package nl.tomjansen.loopgain.service.project;

import nl.tomjansen.loopgain.dto.model.project.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAllProjects();
    ProjectDto getProject(Long id);
    Long postProject(ProjectDto dto);
    ProjectDto deleteProject(Long projectId);
}
