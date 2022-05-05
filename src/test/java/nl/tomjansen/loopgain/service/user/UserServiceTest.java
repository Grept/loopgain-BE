package nl.tomjansen.loopgain.service.user;

import nl.tomjansen.loopgain.dto.mapper.UserMapper;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.model.user.Authority;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.user.UserRepository;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@WithMockUser(username = "testuser", password = "123pass", authorities = "PROJECT_HOST")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user = new User();

    @BeforeEach
    void setUp() {
        user.setUsername("testuser");
        user.setPassword("pass123");
        user.addAuthority(new Authority(user.getUsername(), "PROJECT_HOST"));
    }

    @Test
    @DisplayName("Testing getUserData()")
    void getUserData() {
        Mockito.when(userRepository.findUserByUsername("testuser")).thenReturn(Optional.of(user));

        UserDto returnedUser = userService.getUserData();

        assertEquals(returnedUser.getUsername(), user.getUsername());
        assertEquals(returnedUser.getRole(), user.getAuthorities().stream().toList().get(0).getAuthority());
    }

    @Test
    @DisplayName("Testing creatUser()")
    void createUser() {
        Mockito.when(userRepository.save(any())).thenReturn(user);

        String username = userService.createUser(UserMapper.entityToDto(user));

        assertEquals(username, user.getUsername());
    }
}