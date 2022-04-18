package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.user.AuthDto;
import nl.tomjansen.loopgain.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @PostMapping("/auth")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) {
//        System.out.println("Running signIn method");
//        System.out.println(authDto.toString());
        UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        Authentication auth = authManager.authenticate(up);

        UserDetails ud = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(ud);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        System.out.println(token);
        return new ResponseEntity<>("Token generated", headers, HttpStatus.OK);

//        return ResponseEntity.ok()
//                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
//                .body("Token generated");
    }
}
