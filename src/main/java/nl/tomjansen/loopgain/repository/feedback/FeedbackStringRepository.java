package nl.tomjansen.loopgain.repository.feedback;

import nl.tomjansen.loopgain.model.feedback.FeedbackString;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FeedbackStringRepository extends JpaRepository<FeedbackString, Long> {
    Optional<FeedbackString> findFeedbackStringByMediaFileIdAndReviewerUsername(Long mediaFileId, String reviewerUsername);
}
