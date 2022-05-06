package nl.tomjansen.loopgain.config;

import nl.tomjansen.loopgain.repository.user.UserRepository;
import nl.tomjansen.loopgain.security.JwtRequestFilter;
import nl.tomjansen.loopgain.security.JwtService;
import nl.tomjansen.loopgain.service.user.CustomUserDetailsService;
import nl.tomjansen.loopgain.service.user.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestConfig {

    @MockBean
    public UserRepository userRepository;

    @MockBean
    public JwtService jwtService;

    @MockBean
    public CustomUserDetailsService customUserDetailsService;

    @MockBean
    public JwtRequestFilter jwtRequestFilter;

}
