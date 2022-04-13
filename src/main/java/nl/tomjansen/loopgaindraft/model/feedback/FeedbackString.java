package nl.tomjansen.loopgaindraft.model.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nl.tomjansen.loopgaindraft.model.media.Media;
import nl.tomjansen.loopgaindraft.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "feedback_collection")
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Comment c : commentsList) {
            sb
                    .append(c.getTimeStamp())
                    .append(":/n")
                    .append(c.getCommentText())
                    .append("/n/n");
        }

        return sb.toString();
    }

    public void addComment(Comment comment) {
        this.commentsList.add(comment);
    }
}
