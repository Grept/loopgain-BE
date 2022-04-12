package nl.tomjansen.loopgaindraft.service.media;

import nl.tomjansen.loopgaindraft.dto.model.media.MediaDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MediaService {
    Long saveMedia(String fileName, MultipartFile file) throws IOException;

    MediaDto getMedia(Long id);
}
