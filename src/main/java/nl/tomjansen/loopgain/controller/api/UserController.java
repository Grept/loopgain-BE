package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.user.UserDto;
import nl.tomjansen.loopgain.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserData")
    public ResponseEntity<Object> getUserData() {

        return new ResponseEntity<>(userService.getUserData(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        System.out.println(String.format("DTO password: %s", userDto.getPassword()));

        Long id = userService.createUser(userDto);

        return new ResponseEntity<Object>(String.format("Created new user with ID: %d.", id), HttpStatus.CREATED);
    }

}
