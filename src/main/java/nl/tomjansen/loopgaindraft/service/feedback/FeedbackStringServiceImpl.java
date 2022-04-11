package nl.tomjansen.loopgaindraft.service.feedback;

import nl.tomjansen.loopgaindraft.dto.mapper.FeedbackStringMapper;
import nl.tomjansen.loopgaindraft.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgaindraft.repository.feedback.FeedbackStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackStringServiceImpl implements FeedbackStringService{

    @Autowired
    private FeedbackStringRepository repo;

    @Override
    public FeedbackStringDto getFeedbackString(Long id) {
        return FeedbackStringMapper.toDto(repo.getById(id));
    }
}
