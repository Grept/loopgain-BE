package nl.tomjansen.loopgaindraft.dto.model.media;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgaindraft.model.media.MediaType;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class VideoDto {
    private String fileName;
    private MediaType mediaType;
    private byte[] data;
}
