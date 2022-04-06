package nl.tomjansen.loopgaindraft.model.project;

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
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String projectName;

    @ManyToOne
    private User projectOwner;

    @OneToMany
    private List<Media> projectMedia = new ArrayList<>();

}
