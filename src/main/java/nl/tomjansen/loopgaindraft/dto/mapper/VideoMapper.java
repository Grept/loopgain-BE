package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.dto.model.media.VideoDto;
import nl.tomjansen.loopgaindraft.model.media.Video;
import org.springframework.web.multipart.MultipartFile;

public abstract class VideoMapper {

    public static VideoDto entityToDto(Video video) {
        return new VideoDto()
                .setFileName(video.getFileName());
    }
}
