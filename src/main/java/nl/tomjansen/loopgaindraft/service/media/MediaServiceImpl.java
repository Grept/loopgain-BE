package nl.tomjansen.loopgaindraft.service.media;

import nl.tomjansen.loopgaindraft.dto.mapper.MediaMapper;
import nl.tomjansen.loopgaindraft.dto.model.media.MediaDto;
import nl.tomjansen.loopgaindraft.exception.MediaAlreadyExistsException;
import nl.tomjansen.loopgaindraft.exception.RecordNotFoundException;
import nl.tomjansen.loopgaindraft.model.media.Media;
import nl.tomjansen.loopgaindraft.model.project.Project;
import nl.tomjansen.loopgaindraft.repository.media.MediaContentStore;
import nl.tomjansen.loopgaindraft.repository.media.MediaRepository;
import nl.tomjansen.loopgaindraft.repository.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    MediaContentStore contentStore;

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
