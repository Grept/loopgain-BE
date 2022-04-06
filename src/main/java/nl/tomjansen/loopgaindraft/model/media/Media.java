package nl.tomjansen.loopgaindraft.model.media;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Media {

    @Id
    @GeneratedValue
    private Long id;

    private MediaType mediaType;

    private byte[] byteArray;

}
