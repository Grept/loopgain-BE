package nl.tomjansen.loopgain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tomjansen.loopgain.model.project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String emailadress;

    private String password;

//    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
//    private List<FeedbackString> feedbackStrings;

    @OneToMany(mappedBy = "projectOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> projectList = new ArrayList<>();

    private String role;

    // Ik gebruik hier een Set ipv een List. Een list kan duplicate waardes bevatten en een set alleen unieke waardes.
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            mappedBy = "username"
    )
    private Set<Authority> authorities;
}
