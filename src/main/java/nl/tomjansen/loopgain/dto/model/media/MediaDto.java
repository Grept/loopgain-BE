package nl.tomjansen.loopgain.dto.model.media;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.core.io.InputStreamResource;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class MediaDto {
    private Long id;
    private String fileName;
    private LocalDateTime creationDateTime;
    private String contentId;
    private long contentLength;
    private String contentMimeType;

    // Deze DTO bevat ook een inputstream. Dit is wat er uiteindelijk terug gegeven gaat worden aan de ResponseEntity.
    // De DTO verschilt hierin van het model.
    private InputStreamResource inputStreamResource;
}