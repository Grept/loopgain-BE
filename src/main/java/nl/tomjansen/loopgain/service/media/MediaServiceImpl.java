package nl.tomjansen.loopgain.service.media;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.mapper.MediaMapper;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.exception.MediaAlreadyExistsException;
import nl.tomjansen.loopgain.exception.RecordNotFoundException;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.repository.media.MediaContentStore;
import nl.tomjansen.loopgain.repository.media.MediaRepository;
import nl.tomjansen.loopgain.repository.project.ProjectRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final ProjectRepository projectRepository;
    private final MediaRepository mediaRepository;
    private final MediaContentStore contentStore;

    @Override
    public Long saveMedia(String fileName, MultipartFile file, Long projectId) throws IOException {

        Optional<Project> projectOptional = projectRepository.findById(projectId);

        if(projectOptional.isPresent()) {
            if (mediaRepository.existsByFileName(fileName)) {
                throw new MediaAlreadyExistsException();
            }

            Media media = MediaMapper.mpfToEntity(fileName, file);
            media.setProject(projectOptional.get());
            contentStore.setContent(media, file.getInputStream());
            return mediaRepository.save(media).getId();
        } else {
            throw new RecordNotFoundException(String.format("Project with ID: %d was not found.", projectId));
        }
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

    @Override
    public List<String> listAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        List<String> mediaNameList = new ArrayList<>();

        for (Media m : mediaList) {
            mediaNameList.add(m.getFileName());
        }

        return mediaNameList;
    }

    @Override
    public void deleteFile(Long mediaId) {
        Optional<Media> mediaFile = mediaRepository.findById(mediaId);
        if(mediaFile.isPresent()) {
            mediaRepository.delete(mediaFile.get());
        } else {
            throw new RecordNotFoundException("Media file not found.");
        }
    }
}