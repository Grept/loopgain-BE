package nl.tomjansen.loopgaindraft.service;

import nl.tomjansen.loopgaindraft.dto.mapper.ProjectMapper;
import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.model.project.Project;
import nl.tomjansen.loopgaindraft.model.user.User;
import nl.tomjansen.loopgaindraft.repository.project.ProjectRepository;
import nl.tomjansen.loopgaindraft.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<ProjectDto> getAllProjects(User user) {
        List<Project> projectList = userRepository.getById(user.getId()).getProjectList();
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project p : projectList) {
            projectDtoList.add(ProjectMapper.toDto(p));
        }

        return projectDtoList;
    }

    @Override
    public ProjectDto getProject(Long id) {
        return ProjectMapper.toDto(projectRepository.getById(id));
    }

    @Override
    public Long postProject(ProjectDto dto) {
        return null;
    }

    @Override
    public ProjectDto updateProject(ProjectDto dto) {
        return null;
    }

    @Override
    public Long deleteProject(ProjectDto dto) {
        return null;
    }
}
