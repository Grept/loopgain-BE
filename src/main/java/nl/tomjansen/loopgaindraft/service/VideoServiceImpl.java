package nl.tomjansen.loopgaindraft.service;

import nl.tomjansen.loopgaindraft.dto.mapper.VideoMapper;
import nl.tomjansen.loopgaindraft.dto.model.media.VideoDto;
import nl.tomjansen.loopgaindraft.exception.MediaAlreadyExistsException;
import nl.tomjansen.loopgaindraft.exception.RecordNotFoundException;
import nl.tomjansen.loopgaindraft.model.media.Video;
import nl.tomjansen.loopgaindraft.repository.media.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoRepository videoRepository;

    // Get one video
    @Override
    public VideoDto getVideo(String fileName) {
        if(!videoRepository.existsByFileName(fileName)) {
            throw new RecordNotFoundException();
        } else {
            return VideoMapper.entityToDto(videoRepository.findByFileName(fileName));
        }
    }

    // Save one video
    @Override
    public Long saveVideo(MultipartFile file, String fileName) throws IOException {
        if(videoRepository.existsByFileName(fileName)) {
            throw new MediaAlreadyExistsException();
        } else {
            return videoRepository.save(new Video(fileName, file.getBytes())).getId();
        }
    }

    // Delete a video
    @Override
    public void deleteVideo(String fileName) {
        if(!videoRepository.existsByFileName(fileName)) {
            throw new RecordNotFoundException();
        } else {
            videoRepository.deleteByFileName(fileName);
        }
    }

    @Override
    public List<String> getAllVideoNames() {
        return null;
    }
}
