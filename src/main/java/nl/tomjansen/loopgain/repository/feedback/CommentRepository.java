package nl.tomjansen.loopgain.repository.feedback;

import nl.tomjansen.loopgain.model.feedback.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public boolean existsCommentByCommentTextAndTimeStamp(String commentText, Double timeStamp);
}
