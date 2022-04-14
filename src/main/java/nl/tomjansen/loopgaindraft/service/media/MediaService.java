package nl.tomjansen.loopgaindraft.service.media;

import nl.tomjansen.loopgaindraft.dto.model.media.MediaDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {
    Long saveMedia(String fileName, MultipartFile file, Long projectId) throws IOException;

    MediaDto getMedia(Long id);

    List<String> listAllMedia();

    void deleteFile(Long mediaId);
}
