package nl.tomjansen.loopgain.repository.feedback;

import nl.tomjansen.loopgain.model.feedback.FeedbackString;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackStringRepository extends JpaRepository<FeedbackString, Long> {
}
