package nl.tomjansen.loopgaindraft.service;

import nl.tomjansen.loopgaindraft.repository.media.AudioRepository;
import nl.tomjansen.loopgaindraft.repository.media.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService{

    @Autowired
    AudioRepository audioRepository;

    @Autowired
    VideoRepository videoRepository;
}
