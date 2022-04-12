package nl.tomjansen.loopgaindraft.service.media;

import nl.tomjansen.loopgaindraft.dto.mapper.MediaMapper;
import nl.tomjansen.loopgaindraft.dto.model.media.MediaDto;
import nl.tomjansen.loopgaindraft.exception.RecordNotFoundException;
import nl.tomjansen.loopgaindraft.model.media.Media;
import nl.tomjansen.loopgaindraft.repository.media.MediaContentStore;
import nl.tomjansen.loopgaindraft.repository.media.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    MediaContentStore contentStore;

    @Override
    public Long saveMedia(String fileName, MultipartFile file) throws IOException {
        Media media = MediaMapper.mpfToEntity(fileName, file);

        contentStore.setContent(media, file.getInputStream());

        return mediaRepository.save(media).getId();
    }

    @Override
    public MediaDto getMedia(Long id) {
        Optional<Media> m = mediaRepository.findById(id);
        if(m.isPresent()) {
            return MediaMapper.entityToDto(m.get(), new InputStreamResource(contentStore.getContent(m.get())));
        } else {
            throw new RecordNotFoundException("Media file not found.");
        }
    }
}
