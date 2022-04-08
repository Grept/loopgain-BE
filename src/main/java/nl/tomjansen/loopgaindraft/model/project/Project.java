package nl.tomjansen.loopgaindraft.model.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgaindraft.model.media.Media;
import nl.tomjansen.loopgaindraft.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String projectName;

    private String director;

    private String producer;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User projectOwner;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Media> projectMedia = new ArrayList<>();

}
