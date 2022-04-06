package nl.tomjansen.loopgaindraft.model.feedback;

import lombok.Getter;
import lombok.Setter;
import nl.tomjansen.loopgaindraft.model.media.Media;

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

    @OneToMany
    private List<Comment> comments = new ArrayList<Comment>();

    @ManyToOne
    private Media mediaFile;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Comment c : comments) {
            sb
                    .append(c.getTimeStamp())
                    .append(":/n")
                    .append(c.getComment())
                    .append("/n/n");
        }

        return sb.toString();
    }
}
