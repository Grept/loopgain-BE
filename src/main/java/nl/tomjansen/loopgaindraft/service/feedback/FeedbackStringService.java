package nl.tomjansen.loopgaindraft.service.feedback;

import nl.tomjansen.loopgaindraft.dto.model.feedback.FeedbackStringDto;

public interface FeedbackStringService {

    public FeedbackStringDto getFeedbackString(Long id);

    public Long createFeedbackString();

    public Long deleteFeedbackString(Long id);
}
