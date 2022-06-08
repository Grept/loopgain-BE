package nl.tomjansen.loopgain.service.feedback;

import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.model.feedback.Comment;
import java.util.List;

public interface FeedbackStringService {
    FeedbackStringDto getUserFeedbackString(Long id);
    Long createFeedbackString(Long mediaId);
    Long deleteFeedbackString(Long id);
    Long createFilledFeedbackString(Long mediaId, List<CommentDto> commentList);
    void deleteCommentFromFeedbackString(Comment comment, Long feedbackStringId);
}
