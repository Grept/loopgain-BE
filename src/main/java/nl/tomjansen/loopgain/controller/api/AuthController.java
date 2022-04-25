package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.user.AuthDto;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.security.JwtService;
import nl.tomjansen.loopgain.service.user.CustomUserDetailsService;
import nl.tomjansen.loopgain.service.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000/", exposedHeaders = "Authorization")
public class AuthController {

    // Dependendy Injection
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) throws Exception {

        String username = authDto.getUsername();
        String password = authDto.getPassword();

//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//        } catch (BadCredentialsException exception) {
//            throw new Exception("Incorrect username or password", exception);
//        }

        UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        Authentication auth = authenticationManager.authenticate(up);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();


//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authDto.getUsername());
        String token = jwtService.generateToken(userDetails);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return new ResponseEntity<>("Token generated", headers, HttpStatus.OK);
    }

}
