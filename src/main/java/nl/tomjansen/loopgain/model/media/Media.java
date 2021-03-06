package nl.tomjansen.loopgain.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tomjansen.loopgain.model.feedback.FeedbackString;
import nl.tomjansen.loopgain.model.project.Project;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Media {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(unique = true)
    private String fileName;

    private LocalDateTime creationDateTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "project_media_id")
    private Project project;

    @OneToMany(mappedBy = "mediaFile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FeedbackString> feedbackCollection = new ArrayList<>();

    @ContentId
    private String contentId;

    @ContentLength
    private long contentLength;

    private String contentMimeType = "";
}
