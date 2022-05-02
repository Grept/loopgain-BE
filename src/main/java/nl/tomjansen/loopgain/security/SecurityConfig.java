package nl.tomjansen.loopgain.security;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.service.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()

                // Auth API
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()

                // Project API
                .antMatchers(HttpMethod.GET, "/user/projects/{projectId:[\\d+]}").hasAuthority("PROJECT_HOST")
                .antMatchers(HttpMethod.DELETE, "/user/projects/{projectId:[\\d+]}").hasAuthority("PROJECT_HOST")
                .antMatchers(HttpMethod.GET, "/user/projects").hasAuthority("PROJECT_HOST")
                .antMatchers(HttpMethod.POST, "/user/projects").hasAuthority("PROJECT_HOST")

                // Media API
                .antMatchers(HttpMethod.POST,"/project/{projectId:[\\d+]}/media").hasAuthority("PROJECT_HOST")
                .antMatchers(HttpMethod.GET,"/media/{mediaId:[\\d+]}/data").hasAnyAuthority("PROJECT_HOST", "REVIEWER")
                .antMatchers(HttpMethod.DELETE,"/media/{mediaId:[\\d+]}").hasAuthority("PROJECT_HOST")
                .antMatchers(HttpMethod.GET,"/media/{mediaId:[\\d+]}").hasAnyAuthority("PROJECT_HOST", "REVIEWER")

                // Feedback API
                .antMatchers(HttpMethod.POST,"/media/{mediaId:[\\d+]}/feedback").hasAuthority("REVIEWER")
                .antMatchers(HttpMethod.DELETE, "/feedback/{feedbackStringId:[\\d+]}").hasAuthority("REVIEWER")

                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class)

                .csrf().disable()
                .cors();
    }
}
