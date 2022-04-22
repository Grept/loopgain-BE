package nl.tomjansen.loopgain.model.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthorityId implements Serializable {
    private String username;
    private String authority;

    public AuthorityId(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public AuthorityId() {
    }
}
