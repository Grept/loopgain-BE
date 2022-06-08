package nl.tomjansen.loopgain.dto.model.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class FeedbackStringDto {
    private Long id;
    private List<CommentDto> commentList;
    private MediaDto mediaDto;
    private String reviewer;
}
