package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgaindraft.model.feedback.FeedbackString;

public class FeedbackStringMapper {

    public static FeedbackStringDto toDto(FeedbackString feedbackString) {
        return new FeedbackStringDto()
                .setComments(feedbackString.getComments())
                .setMediaFile(feedbackString.getMediaFile());
    }
}
