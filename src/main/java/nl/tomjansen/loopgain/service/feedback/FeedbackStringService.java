package nl.tomjansen.loopgain.service.feedback;

import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;

public interface FeedbackStringService {

    public FeedbackStringDto getFeedbackString(Long id);

    public Long createFeedbackString(Long mediaId);

    public Long deleteFeedbackString(Long id);
}
