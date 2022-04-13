package nl.tomjansen.loopgaindraft.service.feedback;

import nl.tomjansen.loopgaindraft.dto.model.feedback.CommentDto;

public interface CommentService {
    void saveComment(CommentDto commentDto, Long feedbackStringId);
}
