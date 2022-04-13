package nl.tomjansen.loopgaindraft.dto.model.feedback;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
public class CommentDto implements Serializable {
    private String commentText;
    private Timestamp timeStamp;
}
