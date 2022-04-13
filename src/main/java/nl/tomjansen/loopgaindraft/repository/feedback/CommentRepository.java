package nl.tomjansen.loopgaindraft.repository.feedback;

import nl.tomjansen.loopgaindraft.model.feedback.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
