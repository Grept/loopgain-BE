package nl.tomjansen.loopgain.service.feedback;

import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;

public interface CommentService {
    void saveComment(CommentDto commentDto, Long feedbackStringId);
    void deleteComment(Long mediaId, Long commentId);
}
