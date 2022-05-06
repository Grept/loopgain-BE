package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.mapper.UserMapper;
import nl.tomjansen.loopgain.model.user.Authority;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@WebMvcTest(UserController.class)
@WithMockUser(username = "testuser", password = "123pass", authorities = "PROJECT_HOST")
@ContextConfiguration(classes = {TestConfiguration.class})
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    private User user = new User();

    @BeforeEach
    void setUp() {
        user.setUsername("testuser");
        user.setPassword("123pass");
        user.addAuthority(new Authority(user.getUsername(), "PROJECT_HOST"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetUserData() throws Exception {
        Mockito.when(userService.getUserData()).thenReturn(UserMapper.entityToDto(user));

        mockMvc
                .perform(get("/getUserData"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("testuser")));

    }

    @Test
    void shouldRegisterUser() {
    }
}
