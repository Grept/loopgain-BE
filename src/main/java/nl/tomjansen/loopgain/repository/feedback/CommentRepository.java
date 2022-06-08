package nl.tomjansen.loopgain.repository.feedback;

import nl.tomjansen.loopgain.model.feedback.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsCommentByCommentTextAndTimeStamp(String commentText, Double timeStamp);
    Optional<Comment> findCommentByCommentTextAndTimeStampAndFeedbackStringId(String commentText, Double timeStamp, Long fbsId);
}
