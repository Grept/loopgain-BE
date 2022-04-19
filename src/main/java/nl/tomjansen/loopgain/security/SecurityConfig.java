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
import org.springframework.security.core.userdetails.UserDetailsService;
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

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//                .inMemoryAuthentication()
//                .withUser("tomjansen").password(passwordEncoder().encode("testpass")).authorities("PROJECT_HOST")
//
//                .and()
//                .withUser("roosnetjes").password(passwordEncoder().encode("testpass")).authorities("REVIEWER");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()

                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/auth").permitAll()
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/register").permitAll()
                .and().authorizeRequests().antMatchers("/user/projects").hasAuthority("PROJECT_HOST")
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE,"/user/projects/*").fullyAuthenticated()

                .and()
                .authorizeRequests().anyRequest().authenticated()


                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)

                .csrf().disable()
                .cors();
    }
}
