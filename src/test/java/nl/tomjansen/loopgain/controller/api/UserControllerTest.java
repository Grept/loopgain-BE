package nl.tomjansen.loopgain.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.tomjansen.loopgain.config.LocalDateTimeAdapter;
import nl.tomjansen.loopgain.config.TestConfig;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {TestConfig.class})
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserDto userDto = new UserDto();

    @BeforeEach
    void setUp() {
        userDto.setUsername("testuser");
        userDto.setPassword("xxx-xxx-xxx");
        userDto.setRole("PROJECT_HOST");
        userDto.setProjectDtoList(null);
        userDto.setFeedbackStringDtoList(null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(username = "testuser", password = "123pass", authorities = "PROJECT_HOST")
    void shouldGetUserData() throws Exception {
        Mockito.when(userService.getUserData()).thenReturn(userDto);

        mockMvc
                .perform(get("/getUserData"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("testuser")));
    }

    @Test
    void shouldRegisterUser() throws Exception {
        /* Gson does not work well with LocalDateTime.
         There is a LocalDateTime field nested in the UserDto:
         UserDto -> List<ProjectDto> -> List<MediaDto> -> *MediaDto
         That is why I have to register a custom TypeAdapter.
         (Solution found on stackoverflow by Drux)*/
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        String json = gson.toJson(userDto);
        System.out.println(json);


        Mockito.when(userService.createUser(any())).thenReturn("testuser");
        mockMvc
                .perform(post("/register")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content()
                        .string(containsString("Created new user with ID: testuser.")));
    }
}
