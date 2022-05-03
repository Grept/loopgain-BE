package nl.tomjansen.loopgain.controller.api;

import jdk.jfr.ContentType;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.security.JwtService;
import nl.tomjansen.loopgain.service.project.ProjectService;
import nl.tomjansen.loopgain.service.user.CustomUserDetailsService;
import nl.tomjansen.loopgain.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
@WithMockUser(username = "Tom", password = "pass123", authorities = "PROJECT_HOST")
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService mockProjectService;

//    @MockBean
//    private JwtService jwtService;
//
//    @MockBean
//    private CustomUserDetailsService customUserDetailsService;
//
//    @MockBean
//    private UserService userService;

    private List<ProjectDto> projectList = new ArrayList<>();
    private User user;

    @BeforeEach
    void setUp() {

        projectList.add(
                new ProjectDto()
                        .setId(1L)
                        .setProjectName("La Education de Luca")
                        .setDirector("Salvador Gieling")
                        .setProducer("Witfilm")
                        .setProjectOwner("Tom")
        );

        projectList.add(
                new ProjectDto()
                        .setId(2L)
                        .setProjectName("Sputum")
                        .setDirector("Dan Geesing")
                        .setProducer("Rots Filmwerk")
                        .setProjectOwner("Tom")
        );
    }

    @Test
    void getAllProjects() throws Exception {
        Mockito
                .when(mockProjectService.getAllProjects())
                .thenReturn(projectList);

        mockMvc
                .perform(get("/projects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("List"));
    }

    @Test
//    @WithMockUser(username = "Tom", password = "Tom", authorities = "PROJECT_HOST")
    void shouldGetProject() throws Exception {
        Mockito
                .when(mockProjectService.getProject(1L))
                .thenReturn(projectList.get(0));

        mockMvc
                .perform(get("/projects/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].projectName", is("La Education de Luca")));
    }

    @Test
    void createProject() {
        Mockito
                .when(mockProjectService.postProject(new ProjectDto()))
                .thenReturn(3L);

    }

    @Test
    void deleteProject() {
        Mockito
                .when(mockProjectService.deleteProject(1L))
                .thenReturn(projectList.get(0));
    }
}