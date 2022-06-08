package nl.tomjansen.loopgain.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.tomjansen.loopgain.config.LocalDateTimeAdapter;
import nl.tomjansen.loopgain.config.TestConfig;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.service.project.ProjectService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
@ContextConfiguration(classes = {TestConfig.class})
/* Spring is trying to implement the mocked filter that is declared in TestConfig.class, disabling filters this way
 is a workaround. This is NOT best practice.
 Better would be to also implement the mocked filter and add that mock to MockMvc (manual configuration of MockMvc
 would be required in this case). But that is beyond the scope of this version of the project.
 */
@AutoConfigureMockMvc(addFilters = false)
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
    @DisplayName("Testing getAllProjects(). Should return a list of 2 ProjectDto's")
    void getAllProjects() throws Exception {
        Mockito.when(projectService.getAllProjects()).thenReturn(Lists.newArrayList(projectDto_1, projectDto_2));

        mockMvc
                .perform(get("/user/projects")
                        .contentType(MediaType.APPLICATION_JSON))
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
    @DisplayName("Testing getProject(). Should return a ProjectDto")
    void getProject() throws Exception {
        Mockito.when(projectService.getProject(1L)).thenReturn(projectDto_1);

        mockMvc
                .perform(get("/user/projects/{projectId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.projectName", is("Test Project 1")))
                .andExpect(jsonPath("$.director", is("Director 1")))
                .andExpect(jsonPath("$.producer", is("Producer 1")))
                .andExpect(jsonPath("$.projectOwner", is("testuser")));

    }

    @Test
    @DisplayName("Testing createProject(). Should return String with Project ID.")
    void createProject() throws Exception {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        String json = gson.toJson(projectDto_1);
        Mockito.when(projectService.postProject(any())).thenReturn(projectDto_1.getId());

        mockMvc
                .perform(post("/user/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Project created with ID#: 1")));

    }

    @Test
    @DisplayName("Testing deleteProject. Should return a string naming the deleted project")
    void deleteProject() throws Exception {
        Mockito.when(projectService.deleteProject(any())).thenReturn(projectDto_1);

        mockMvc
                .perform(delete("/user/projects/{projectId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Project with title \"Test Project 1\" was deleted.")));
    }
}