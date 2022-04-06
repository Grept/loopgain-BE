package nl.tomjansen.loopgaindraft.model.media;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tomjansen.loopgaindraft.model.feedback.FeedbackString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Media {

    @Id
    @GeneratedValue
    private Long id;

    private MediaType mediaType;

    @OneToMany
    private List<FeedbackString> feedbackStringCollection;

    private byte[] byteArray;

}
