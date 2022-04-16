package nl.tomjansen.loopgain.controller.request;

import javax.validation.constraints.NotBlank;

public class UserRequest {

    @NotBlank(message = "Field 'username' cannot be blank.")
    private String username;
    private String emailadress;
}
