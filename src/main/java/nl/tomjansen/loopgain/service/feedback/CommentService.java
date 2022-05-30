package nl.tomjansen.loopgain.service.feedback;

import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;

import java.util.List;

public interface CommentService {
    void saveComment(CommentDto commentDto, Long feedbackStringId);
    void deleteComment(CommentDto commentDto, Long mediaId);
}
