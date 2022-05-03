package nl.tomjansen.loopgain.service.project;

import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.project.ProjectRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class ProjectServiceImplTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private User user;
    private List<Project> projectList;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("Tom");

        projectList = new ArrayList<>();

        projectList.add(
                new Project()
                        .setId(1L)
                        .setProjectName("La Education de Luca")
                        .setProjectOwner(user)
                        .setDirector("Salvador Gieling")
                        .setProducer("Witfilm")
                        .setMedia(null));

        projectList.add(
                new Project()
                        .setId(2L)
                        .setProjectName("Sputum")
                        .setProjectOwner(user)
                        .setDirector("Dan Geesin")
                        .setProducer("Rots Filmwerk")
                        .setMedia(null));
    }

    @AfterEach
    void tearDown() {
        projectList = null;
    }

    @Test
    void getAllProjects() {
        Mockito
                .when(projectRepository.findAllByProjectOwner_Username("Tom"))
                .thenReturn(projectList);

//        mockMvc
//                .perform(g)
//                .andDo(print())
//                .andExpect()


    }

    @Test
    void getProject() {
    }

    @Test
    void postProject() {
    }

    @Test
    void deleteProject() {
    }
}