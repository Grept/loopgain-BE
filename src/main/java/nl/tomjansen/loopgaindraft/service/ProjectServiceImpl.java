package nl.tomjansen.loopgaindraft.service;

import nl.tomjansen.loopgaindraft.dto.mapper.ProjectMapper;
import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.repository.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository repo;

    @Override
    public ProjectDto getProject(Long id) {
        return ProjectMapper.toDto(repo.getById(id));
    }

    @Override
    public Long postProject(ProjectDto dto) {
        return null;
    }
}
