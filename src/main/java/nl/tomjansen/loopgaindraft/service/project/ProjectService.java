package nl.tomjansen.loopgaindraft.service.project;

import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.model.user.User;

import java.util.List;

public interface ProjectService {

    public List<ProjectDto> getAllProjects();

    public ProjectDto getProject(Long id);

    public Long postProject(ProjectDto dto);

    public ProjectDto updateProject(ProjectDto dto, Long projectId);

    public Long deleteProject(Long projectId);
}
