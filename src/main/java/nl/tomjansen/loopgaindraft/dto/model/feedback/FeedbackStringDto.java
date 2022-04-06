package nl.tomjansen.loopgaindraft.dto.model.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgaindraft.model.feedback.Comment;
import nl.tomjansen.loopgaindraft.model.media.Media;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class FeedbackStringDto {

    private List<Comment> comments = new ArrayList<>();

    private Media mediaFile;
}
