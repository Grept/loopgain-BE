package nl.tomjansen.loopgaindraft.service.feedback;

import nl.tomjansen.loopgaindraft.dto.mapper.CommentMapper;
import nl.tomjansen.loopgaindraft.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgaindraft.exception.RecordNotFoundException;
import nl.tomjansen.loopgaindraft.model.feedback.Comment;
import nl.tomjansen.loopgaindraft.model.feedback.FeedbackString;
import nl.tomjansen.loopgaindraft.repository.feedback.CommentRepository;
import nl.tomjansen.loopgaindraft.repository.feedback.FeedbackStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired private CommentRepository commentRepository;
    @Autowired private FeedbackStringRepository feedbackStringRepository;

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
