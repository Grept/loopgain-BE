package nl.tomjansen.loopgain.model.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

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

    private Double timeStamp;

    @ManyToOne
    @JoinColumn(name = "feedback_comment_id")
    private FeedbackString feedbackString;

    public Comment(String commentText, Double timeStamp) {
        this.commentText = commentText;
        this.timeStamp = timeStamp;
    }
}
