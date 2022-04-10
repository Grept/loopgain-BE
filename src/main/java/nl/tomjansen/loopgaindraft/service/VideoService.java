package nl.tomjansen.loopgaindraft.service;

import nl.tomjansen.loopgaindraft.dto.model.media.VideoDto;
import nl.tomjansen.loopgaindraft.model.media.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {

    VideoDto getVideo(String fileName);

    Long saveVideo(MultipartFile file, String fileName) throws IOException;

    void deleteVideo(String fileName);

    List<String> getAllVideoNames();
}
