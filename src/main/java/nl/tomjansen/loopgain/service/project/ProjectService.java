package nl.tomjansen.loopgain.service.project;

import nl.tomjansen.loopgain.dto.model.project.ProjectDto;

import java.util.List;

public interface ProjectService {

    public List<ProjectDto> getAllProjects();

    public ProjectDto getProject(Long id);

    public Long postProject(ProjectDto dto);

    public ProjectDto updateProject(ProjectDto dto, Long projectId);

    public ProjectDto deleteProject(Long projectId);
}
