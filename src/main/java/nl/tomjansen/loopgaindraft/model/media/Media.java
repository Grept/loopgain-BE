package nl.tomjansen.loopgaindraft.model.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tomjansen.loopgaindraft.model.feedback.FeedbackString;
import nl.tomjansen.loopgaindraft.model.project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Media {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(unique = true)
    private String fileName;

    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "project_media_id")
    private Project project;

    @OneToMany(mappedBy = "mediaFile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FeedbackString> feedbackCollection = new ArrayList<>();

    @Lob
//    @Column(columnDefinition = "BLOB")
    private byte[] data;

    public Media(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }

}
