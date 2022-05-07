package nl.tomjansen.loopgain.controller.api;

import com.google.gson.Gson;
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
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    private final UserDto userDto = new UserDto();
    private final Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        userDto.setUsername("testuser");
        userDto.setPassword("xxx-xxx-xxx");
        userDto.setRole("PROJECT_HOST");

        System.out.println(gson.toJson(userDto));

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
        Mockito.when(userService.createUser(userDto)).thenReturn("testuser");

        mockMvc
                .perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(userDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content()
                        .string(containsString("Created new user with ID: testuser.")));
    }
}
