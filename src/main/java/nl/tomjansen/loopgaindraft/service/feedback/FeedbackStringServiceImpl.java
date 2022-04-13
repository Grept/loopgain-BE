package nl.tomjansen.loopgaindraft.service.feedback;

import nl.tomjansen.loopgaindraft.dto.mapper.FeedbackStringMapper;
import nl.tomjansen.loopgaindraft.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgaindraft.exception.RecordNotFoundException;
import nl.tomjansen.loopgaindraft.model.feedback.FeedbackString;
import nl.tomjansen.loopgaindraft.repository.feedback.FeedbackStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackStringServiceImpl implements FeedbackStringService{

    @Autowired
    private FeedbackStringRepository feedbackStringRepository;

    @Override
    public FeedbackStringDto getFeedbackString(Long id) {
        Optional<FeedbackString> feedbackString = feedbackStringRepository.findById(id);
        if(feedbackString.isPresent()) {
            FeedbackStringDto feedbackStringDto = FeedbackStringMapper.entityToDto(feedbackString.get());
            return feedbackStringDto;
        } else {
            throw new RecordNotFoundException(String.format("FeedbackString with ID: %d was not found", id));
        }
    }

    @Override
    public Long createFeedbackString() {
        return feedbackStringRepository.save(new FeedbackString()).getId();
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
