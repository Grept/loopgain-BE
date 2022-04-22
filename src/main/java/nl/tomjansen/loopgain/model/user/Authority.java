package nl.tomjansen.loopgain.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@Getter
@Setter
public class Authority {

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
