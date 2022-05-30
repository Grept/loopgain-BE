package nl.tomjansen.loopgain.service.feedback;

import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.model.feedback.Comment;

import java.util.List;

public interface FeedbackStringService {
    public FeedbackStringDto getUserFeedbackString(Long id);
    public Long createFeedbackString(Long mediaId);
    public Long deleteFeedbackString(Long id);
    public Long createFilledFeedbackString(Long mediaId, List<CommentDto> commentList);
    public void deleteCommentFromFeedbackString(Comment comment, Long feedbackStringId);
}
