package nl.tomjansen.loopgain.dto.model.feedback;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
public class CommentDto implements Serializable {
    private Long id;
    private String commentText;
    private Timestamp timeStamp;
}
