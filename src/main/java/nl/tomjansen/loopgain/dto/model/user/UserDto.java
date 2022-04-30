package nl.tomjansen.loopgain.dto.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.model.feedback.FeedbackString;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto {

    @NotNull private String username;
    @NotNull private String password;
    @NotNull private String role;
    private List<ProjectDto> projectDtoList = new ArrayList<>();
    private List<FeedbackStringDto> feedbackStringDtoList = new ArrayList<>();
}
