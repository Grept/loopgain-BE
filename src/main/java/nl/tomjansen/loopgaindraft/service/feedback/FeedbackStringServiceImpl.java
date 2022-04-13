package nl.tomjansen.loopgaindraft.service.feedback;

import nl.tomjansen.loopgaindraft.dto.mapper.FeedbackStringMapper;
import nl.tomjansen.loopgaindraft.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgaindraft.exception.RecordNotFoundException;
import nl.tomjansen.loopgaindraft.model.feedback.FeedbackString;
import nl.tomjansen.loopgaindraft.model.media.Media;
import nl.tomjansen.loopgaindraft.repository.feedback.FeedbackStringRepository;
import nl.tomjansen.loopgaindraft.repository.media.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackStringServiceImpl implements FeedbackStringService{

    @Autowired
    private FeedbackStringRepository feedbackStringRepository;

    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public FeedbackStringDto getFeedbackString(Long id) {
        Optional<FeedbackString> feedbackStringOptional = feedbackStringRepository.findById(id);
        if(feedbackStringOptional.isPresent()) {
            FeedbackStringDto feedbackStringDto = FeedbackStringMapper.entityToDto(feedbackStringOptional.get());
            return feedbackStringDto;
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
}
