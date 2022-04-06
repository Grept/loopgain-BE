package nl.tomjansen.loopgaindraft.model.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    private Timestamp timeStamp = new Timestamp(Instant.now().toEpochMilli());

    @ManyToOne
    private FeedbackString feedbackString;
}
