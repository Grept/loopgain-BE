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

@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {

    @Id
    private String username;

    private String password;

    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FeedbackString> feedbackStringList = new ArrayList<>();

    @OneToMany(mappedBy = "projectOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Project> projectList = new ArrayList<>();

    /*
    * I use a Set instead of a List. A list can contain duplicate values, a set can only hold unique values.
    * */
    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    /*
    * In this case I don't use Lombok to provide Getters and Setters beceause I want to use my own implementations to
    * get and set the autorities field.
    * */
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
