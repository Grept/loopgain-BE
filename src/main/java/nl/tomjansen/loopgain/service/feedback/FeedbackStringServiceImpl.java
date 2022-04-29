package nl.tomjansen.loopgain.service.feedback;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.mapper.FeedbackStringMapper;
import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.exception.RecordNotFoundException;
import nl.tomjansen.loopgain.model.feedback.FeedbackString;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.repository.feedback.FeedbackStringRepository;
import nl.tomjansen.loopgain.repository.media.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackStringServiceImpl implements FeedbackStringService{

    private final FeedbackStringRepository feedbackStringRepository;
    private final MediaRepository mediaRepository;
    private final CommentService commentService;

    @Override
    public FeedbackStringDto getFeedbackString(Long id) {
        Optional<FeedbackString> feedbackStringOptional = feedbackStringRepository.findById(id);
        if(feedbackStringOptional.isPresent()) {
            return FeedbackStringMapper.entityToDto(feedbackStringOptional.get());
        } else {
            throw new RecordNotFoundException(String.format("FeedbackString with ID: %d was not found", id));
        }
    }

    @Override
    public Long createFeedbackString(Long mediaId) {
        Optional<Media> mediaOptional = mediaRepository.findById(mediaId);

        if(mediaOptional.isPresent()) {
            FeedbackString feedbackString = new FeedbackString();
            feedbackString.setMediaFile(mediaOptional.get());
            return feedbackStringRepository.save(feedbackString).getId();
        } else {
            throw new RecordNotFoundException(String.format("Mediafile with ID: %d was not found.", mediaId));
        }

    }

    @Override
    public Long deleteFeedbackString(Long feedbackStringId) {
        Optional<FeedbackString> feedbackString = feedbackStringRepository.findById(feedbackStringId);
        if (feedbackString.isPresent()) {
            feedbackStringRepository.delete(feedbackString.get());
            return feedbackStringId;
        } else {
            throw new RecordNotFoundException(String.format("FeedbackString with ID: %d was not found.", feedbackStringId));
        }
    }

    @Override
    public FeedbackStringDto createFilledFeedbackString(Long mediaId, List<CommentDto> commentList) {
        Long feedbackStringId = createFeedbackString(mediaId);

        for(CommentDto commentDto : commentList) {
            commentService.saveComment(commentDto, feedbackStringId);
        }

        return getFeedbackString(feedbackStringId);

    }
}
