package nl.tomjansen.loopgaindraft.service;

import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.model.user.User;

import java.util.List;

public interface ProjectService {

    public List<ProjectDto> getAllProjects(User user);

    public ProjectDto getProject(Long id);

    public Long postProject(ProjectDto dto);

    public ProjectDto updateProject(ProjectDto dto);

    public Long deleteProject(ProjectDto dto);
}
