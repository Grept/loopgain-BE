package nl.tomjansen.loopgain.model.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.model.user.User;

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
    private List<Media> media = new ArrayList<>();

}
