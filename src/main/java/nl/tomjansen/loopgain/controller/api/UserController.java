package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000/")
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserData")
    public ResponseEntity<Object> getUserData() {

        return new ResponseEntity<>(userService.getUserData(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDto userDto) {
        String username = userService.createUser(userDto);

        return new ResponseEntity<>(String.format("Created new user with ID: %s.", username), HttpStatus.CREATED);
    }

}
