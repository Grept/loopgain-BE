package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@RequiredArgsConstructor
@WebMvcTest
public class UserControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    UserService mockUserService;

    @Test
    public void shouldReturnUserData() throws Exception {

    }
}
