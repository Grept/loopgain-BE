package nl.tomjansen.loopgaindraft.model.media;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Video extends Media {

    public Video(String fileName, byte[] data) {
        super(fileName, data);
    }

}
