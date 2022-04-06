package nl.tomjansen.loopgaindraft.repository.feedback;

import nl.tomjansen.loopgaindraft.model.feedback.FeedbackString;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackStringRepository extends JpaRepository<FeedbackString, Long> {
}
