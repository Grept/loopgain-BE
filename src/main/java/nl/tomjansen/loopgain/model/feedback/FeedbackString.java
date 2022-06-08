package nl.tomjansen.loopgain.model.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.model.user.User;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "feedbackString")
public class FeedbackString {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "feedbackString", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> commentsList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "media_feedback_id")
    private Media mediaFile;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User reviewer;

    public void removeComment(Comment comment) {
        commentsList.remove(comment);
    }
}
