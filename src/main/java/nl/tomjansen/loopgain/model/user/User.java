package nl.tomjansen.loopgain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import nl.tomjansen.loopgain.model.feedback.FeedbackString;
import nl.tomjansen.loopgain.model.project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Getter
//@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

//    @Column(columnDefinition = "serial")
//    private Long id;

    @Id
    private String username;

    private String password;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FeedbackString> feedbackStringList = new ArrayList<>();

    @OneToMany(mappedBy = "projectOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> projectList = new ArrayList<>();

    // Ik gebruik hier een Set ipv een List. Een list kan duplicate waardes bevatten en een set alleen unieke waardes.
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    // Ik gebruik hier geen Lombok voor het maken van getters en setters omdat ik voor het veld authorities mijn eigen
    // implementatie gebruiken wil.
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<FeedbackString> getFeedbackStringList() {
        return feedbackStringList;
    }

    public void setFeedbackStringList(List<FeedbackString> feedbackStrings) {
        this.feedbackStringList = feedbackStrings;
    }

    public List<Project> getProjectList() {
        return projectList;
    }
    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public void removeAuthorities(Authority authority) {
        this.authorities.remove(authority);
    }


}
