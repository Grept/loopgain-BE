package nl.tomjansen.loopgain.service.project;

import nl.tomjansen.loopgain.dto.mapper.ProjectMapper;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.project.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private ProjectMapper projectMapper;

    Project project_1;
    Project project_2;

    @BeforeEach
    void setUp() {
        User user = new User();
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
    @WithMockUser(username = "testuser", password = "123pass", authorities = "PROJECT_HOST")
    void getAllProjects() {
        doReturn(Arrays.asList(project_1, project_2))
                .when(projectRepository).findAllByProjectOwner_Username("testuser");


        List<ProjectDto> projectDtoList = projectService.getAllProjects();

        assertEquals(2, projectDtoList.size(), "getAllProjects() should return 2 projects.");
    }


    @Test
    @DisplayName("Testing getProject() Succes")
    void getProject() {
        doReturn(Optional.of(project_1)).when(projectRepository).findById(1L);


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
    @DisplayName("Testing getProject() Not Found")
    void getProjectNotFound() {
    }

    @Test
    @DisplayName("Testing postProject()")
    void postProject() {
    }

    @Test
    @DisplayName("Testing deleteProject()")
    void deleteProject() {
    }

}