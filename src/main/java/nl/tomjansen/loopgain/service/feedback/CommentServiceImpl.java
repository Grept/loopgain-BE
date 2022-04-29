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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final FeedbackStringRepository feedbackStringRepository;

    @Override
    public void saveComment(CommentDto commentDto, Long feedbackStringId) {
        Optional<FeedbackString> feedbackStringOptional = feedbackStringRepository.findById(feedbackStringId);

        if(feedbackStringOptional.isPresent()) {
            Comment comment = CommentMapper.dtoToEntity(commentDto);
            comment.setFeedbackString(feedbackStringOptional.get());
            commentRepository.save(comment);
        } else {
            throw new RecordNotFoundException(String.format("FeedbackString with ID: %d was not found", feedbackStringId));
        }
    }


}
