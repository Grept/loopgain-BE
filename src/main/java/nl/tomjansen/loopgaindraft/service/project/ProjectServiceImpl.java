package nl.tomjansen.loopgaindraft.service.project;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgaindraft.dto.mapper.ProjectMapper;
import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.exception.RecordNotFoundException;
import nl.tomjansen.loopgaindraft.model.project.Project;
import nl.tomjansen.loopgaindraft.model.user.User;
import nl.tomjansen.loopgaindraft.repository.project.ProjectRepository;
import nl.tomjansen.loopgaindraft.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    // GET ALL
    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectDto> projectDtoList = new ArrayList<>();

        for(Project p : projectList) {
            projectDtoList.add(ProjectMapper.entityToDto(p));
        }

        return projectDtoList;
    }


    // GET ONE
    @Override
    public ProjectDto getProject(Long id) {

        Optional<Project> projectOptional = projectRepository.findById(id);
        if(projectOptional.isPresent()) {
            return ProjectMapper.entityToDto(projectOptional.get());
        } else {
            throw new RecordNotFoundException(String.format("Project with ID#: %d not found", id));
        }
    }


    // POST ONE
    @Override
    public Long postProject(ProjectDto dto) {
        // Create a new Project Entity with the ProjectMapper.dtoToEntity() method.
        // Use the repo to save entity to the db. This also returns an entity.
        // Call the getId() method in order to return the Id to the controller api.
        Project project = projectRepository.save(ProjectMapper.dtoToEntity(dto));
        return project.getId();
    }


    // PUT ONE
    @Override
    public ProjectDto updateProject(ProjectDto dto, Long projectId) {
        return null;
    }


    // DELETE ONE
    @Override
    public Long deleteProject(Long projectId) {
        return null;
    }
}
