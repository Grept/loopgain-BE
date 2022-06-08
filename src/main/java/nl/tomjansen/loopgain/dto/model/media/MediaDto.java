package nl.tomjansen.loopgain.dto.model.media;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import org.springframework.core.io.InputStreamResource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String parentProjectName;
    private String director;
    private String producer;
    private String projectHost;
    private List<FeedbackStringDto> feedbackStringDtoList = new ArrayList<>();

    /*
    * This DTO can eventually be returned to the ResponseEntity (and to the client) and contains an inputstream. This
    * is where the DTO defers from the Entity model, which does not have an inputStream field. The stream is stored
    * in the ContentStore.
    * */
    private InputStreamResource inputStreamResource = null;
}
