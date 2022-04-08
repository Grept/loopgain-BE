package nl.tomjansen.loopgaindraft.model.feedback;

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
public class FeedbackString {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "feedbackString", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "media_feedback_id")
    private Media mediaFile;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Comment c : comments) {
            sb
                    .append(c.getTimeStamp())
                    .append(":/n")
                    .append(c.getCommentText())
                    .append("/n/n");
        }

        return sb.toString();
    }
}
