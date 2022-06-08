package nl.tomjansen.loopgain.service.project;

import nl.tomjansen.loopgain.dto.mapper.ProjectMapper;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.exception.RecordNotFoundException;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.project.ProjectRepository;
import nl.tomjansen.loopgain.repository.user.UserRepository;
import nl.tomjansen.loopgain.service.media.MediaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@WithMockUser(username = "testuser", password = "123pass", authorities = "PROJECT_HOST")
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MediaService mediaService;

    Project project_1;
    Project project_2;
    User user = new User();

    @BeforeEach
    void setUp() {
        user.setUsername("testuser");

        project_1 = new Project()
                .setId(1L)
                .setProjectName("Test Project 1")
                .setDirector("Director 1")
                .setProducer("Producer 1")
                .setProjectOwner(user);

        project_2 = new Project()
                .setId(2L)
                .setProjectName("Test Project 2")
                .setDirector("Director 2")
                .setProducer("Producer 2")
                .setProjectOwner(user);
    }

    @Test
    @DisplayName("Testing ProjectService.getAllProjects(). Should return a list of ProjectDto's.")
    void getAllProjectsTest() {
        doReturn(Arrays.asList(project_1, project_2))
                .when(projectRepository).findAllByProjectOwner_Username("testuser");

        List<ProjectDto> projectDtoList = projectService.getAllProjects();

        assertEquals(2, projectDtoList.size(), "getAllProjects() should return 2 projects.");
    }

    @Test
    @DisplayName("Testing ProjectService.getProject() - SUCCES. Should return a single ProjectDto.")
    void getProjectTest() {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project_1));

        ProjectDto returnedProjectDto = projectService.getProject(1L);

        assertEquals(
                returnedProjectDto.getProjectName(),
                ProjectMapper.entityToDto(project_1).getProjectName(),
                "The projectDto returned was not the same as the mock"
        );

        assertEquals(
                returnedProjectDto.getDirector(),
                ProjectMapper.entityToDto(project_1).getDirector(),
                "The projectDto returned was not the same as the mock"
        );
        assertEquals(
                returnedProjectDto.getProducer(),
                ProjectMapper.entityToDto(project_1).getProducer(),
                "The projectDto returned was not the same as the mock"
        );
        assertEquals(
                returnedProjectDto.getProjectOwner(),
                ProjectMapper.entityToDto(project_1).getProjectOwner(),
                "The projectDto returned was not the same as the mock"
        );
    }

    @Test
    @DisplayName("Testing ProjectService.getProject() - NOT FOUND. Should return a a RecordNotFoundException with message.")
    void getProjectExceptionTest() {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.empty());

        RecordNotFoundException exception = assertThrows(RecordNotFoundException.class, () -> projectService.getProject(1L));

        assertEquals("Project with ID#: 1 not found", exception.getMessage());
    }

    @Test
    @DisplayName("Testing ProjectService.postProject(). Should return a Long (projectId).")
    void postProjectTest() {
        // Mock repositories
        Mockito.when(projectRepository.save(any())).thenReturn(project_1);
        Mockito.when(userRepository.findUserByUsername("testuser")).thenReturn(Optional.of(user));

        // Do action
        Long projectId = projectService.postProject(ProjectMapper.entityToDto(project_1));

        // Assert test
        assertEquals(1L, projectId, "postProject() should return the projectId (1L)");
    }

    @Test
    @DisplayName("Testing ProjectService.deleteProject(). Should return a ProjectDto.")
    void deleteProjectTest() {
        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project_1));
        Mockito.when(mediaService.deleteFile(any())).thenReturn(null);

        ProjectDto projectDto = projectService.deleteProject(1L);

        assertEquals(project_1.getProjectName(), projectDto.getProjectName());
    }

}