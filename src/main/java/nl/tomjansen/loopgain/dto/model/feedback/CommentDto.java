package nl.tomjansen.loopgain.dto.model.feedback;

import lombok.*;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class CommentDto implements Serializable {
    private Long id;
    @NotEmpty private String commentText;
    @NotEmpty private Double timeStamp;
}
