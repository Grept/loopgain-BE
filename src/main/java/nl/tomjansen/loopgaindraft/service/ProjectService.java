package nl.tomjansen.loopgaindraft.service;

import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;

public interface ProjectService {

    public ProjectDto getProject(Long id);

    public Long postProject(ProjectDto dto);
}
