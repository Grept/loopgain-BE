package nl.tomjansen.loopgain.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorities")
@IdClass(AuthorityId.class)
public class Authority implements Serializable {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;

    public Authority(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

}
