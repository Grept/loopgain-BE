package nl.tomjansen.loopgain.service.feedback;

import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;

import java.util.List;

public interface FeedbackStringService {

    public FeedbackStringDto getFeedbackString(Long id);

    public Long createFeedbackString(Long mediaId);

    public Long deleteFeedbackString(Long id);

    FeedbackStringDto createFilledFeedbackString(Long mediaId, List<CommentDto> commentList);
}
