package nl.tomjansen.loopgain.controller.api;

import nl.tomjansen.loopgain.config.TestConfig;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.service.project.ProjectService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
@ContextConfiguration(classes = {TestConfig.class})
@WithMockUser(username = "testuser", password = "123pass", authorities = "PROJECT_HOST")
@AutoConfigureMockMvc(addFilters = false) // Spring is trying to implement the mocked filter, this is a workaround
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    ProjectDto projectDto_1;
    ProjectDto projectDto_2;

    @BeforeEach
    void setUp() {
        projectDto_1 = new ProjectDto()
                .setId(1L)
                .setProjectName("Test Project 1")
                .setDirector("Director 1")
                .setProducer("Producer 1")
                .setProjectOwner("testuser");

        projectDto_2 = new ProjectDto()
                .setId(2L)
                .setProjectName("Test Project 2")
                .setDirector("Director 2")
                .setProducer("Producer 2")
                .setProjectOwner("testuser");
    }

    @Test
    void getAllProjects() throws Exception {
        Mockito.when(projectService.getAllProjects()).thenReturn(Lists.newArrayList(projectDto_1, projectDto_2));

        mockMvc
                .perform(get("/user/projects").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].projectName", is("Test Project 1")))
                .andExpect(jsonPath("$[0].director", is("Director 1")))
                .andExpect(jsonPath("$[0].producer", is("Producer 1")))
                .andExpect(jsonPath("$[0].projectOwner", is("testuser")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].projectName", is("Test Project 2")))
                .andExpect(jsonPath("$[1].director", is("Director 2")))
                .andExpect(jsonPath("$[1].producer", is("Producer 2")))
                .andExpect(jsonPath("$[1].projectOwner", is("testuser")));
    }

    @Test
    void getProject() throws Exception {
        Mockito.when(projectService.getProject(1L)).thenReturn(projectDto_1);

        mockMvc
                .perform(get("/user/projects/{projectId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName", is("Test Project 1")));

    }

    @Test
    void createProject() {
    }

    @Test
    void deleteProject() {
    }
}