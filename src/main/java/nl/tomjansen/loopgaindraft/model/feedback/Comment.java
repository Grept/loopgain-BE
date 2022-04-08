package nl.tomjansen.loopgaindraft.model.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String commentText;

    private Timestamp timeStamp = new Timestamp(Instant.now().toEpochMilli());

    @ManyToOne
    @JoinColumn(name = "feedback_comment_id")
    private FeedbackString feedbackString;
}
