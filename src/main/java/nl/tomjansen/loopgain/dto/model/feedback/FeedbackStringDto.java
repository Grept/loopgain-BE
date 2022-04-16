package nl.tomjansen.loopgain.dto.model.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.model.media.Media;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class FeedbackStringDto {
    private Long id;
    private List<CommentDto> commentList;
    private Media mediaFile;
}
