package nl.tomjansen.loopgaindraft.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tomjansen.loopgaindraft.model.project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "projectOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> projectList = new ArrayList<>();

    private UserRoles role;
}
