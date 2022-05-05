package nl.tomjansen.loopgain.service.project;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.mapper.ProjectMapper;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.exception.RecordNotFoundException;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.project.ProjectRepository;
import nl.tomjansen.loopgain.repository.user.UserRepository;
import nl.tomjansen.loopgain.service.media.MediaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final MediaService mediaService;

    // GET ALL
    @Override
    public List<ProjectDto> getAllProjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        List<Project> projectList = projectRepository.findAllByProjectOwner_Username(userDetails.getUsername());
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
    public Long postProject(ProjectDto projectDto) {
        // Create a new Project Entity with the ProjectMapper.dtoToEntity() method.
        // Use the repo to save entity to the db. This also returns an entity.
        // Call the getId() method in order to return the Id to the controller api.
        Project project = ProjectMapper.dtoToEntity(projectDto);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        System.out.println(userDetails.getUsername());

        Optional<User> userOptional = userRepository.findUserByUsername(userDetails.getUsername());
        if(userOptional.isPresent()) {
            project.setProjectOwner(userOptional.get());
        }

        return projectRepository.save(project).getId();
    }

    // DELETE ONE
    @Override
    public ProjectDto deleteProject(Long projectId) {

        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if(projectOptional.isPresent()) {

            // Make sure all stored media is deleted from the filesystem.
            for(Media m : projectOptional.get().getMedia()) {
                mediaService.deleteFile(m.getId());
            }

            projectRepository.deleteById(projectId);
        } else {
            throw new RecordNotFoundException();
        }

        return ProjectMapper.entityToDto(projectOptional.get());
    }
}
