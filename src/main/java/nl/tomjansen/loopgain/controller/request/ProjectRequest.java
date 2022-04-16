package nl.tomjansen.loopgain.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ProjectRequest {
    @NotBlank(message = "A project name is mandatory.")
    private String projectName;

    private String director;

    private String producer;
}
