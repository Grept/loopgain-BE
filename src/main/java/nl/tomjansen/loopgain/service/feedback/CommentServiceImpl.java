package nl.tomjansen.loopgain.service.feedback;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.mapper.CommentMapper;
import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.exception.RecordNotFoundException;
import nl.tomjansen.loopgain.model.feedback.Comment;
import nl.tomjansen.loopgain.model.feedback.FeedbackString;
import nl.tomjansen.loopgain.repository.feedback.CommentRepository;
import nl.tomjansen.loopgain.repository.feedback.FeedbackStringRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final FeedbackStringRepository feedbackStringRepository;
    private final FeedbackStringService feedbackStringService;

    @Override
    public void saveComment(CommentDto commentDto, Long feedbackStringId) {
        Optional<FeedbackString> feedbackStringOptional = feedbackStringRepository.findById(feedbackStringId);

        if (feedbackStringOptional.isPresent()) {

            // Check if a comment already exists
            if (!commentRepository.existsCommentByCommentTextAndTimeStamp(commentDto.getCommentText(), commentDto.getTimeStamp())) {
                Comment comment = CommentMapper.dtoToEntity(commentDto);
                comment.setFeedbackString(feedbackStringOptional.get());
                commentRepository.save(comment);
            }
        } else {
            throw new RecordNotFoundException(String.format("FeedbackString with ID: %d was not found", feedbackStringId));
        }
    }

    @Override
    public void deleteComment(Long mediaId, Long commentId) {
        Long feedbackStringId = feedbackStringService.getUserFeedbackString(mediaId).getId();

        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if(commentOptional.isPresent()) {
            // First delete comment from commentList in associated FeedbackString
            feedbackStringService.deleteCommentFromFeedbackString(commentOptional.get(), feedbackStringId);

            // Then delete comment entity form DB
            commentRepository.deleteById(commentOptional.get().getId());
        } else {
            throw new RecordNotFoundException("Comment was not found. Nothing deleted.");
        }
    }
}
