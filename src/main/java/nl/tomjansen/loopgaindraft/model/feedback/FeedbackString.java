package nl.tomjansen.loopgaindraft.model.feedback;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FeedbackString {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Comment> comments = new ArrayList<Comment>();

}
