package nl.tomjansen.loopgaindraft.controller.api;

import nl.tomjansen.loopgaindraft.dto.model.media.MediaDto;
import nl.tomjansen.loopgaindraft.exception.MediaAlreadyExistsException;
import nl.tomjansen.loopgaindraft.repository.media.MediaRepository;
import nl.tomjansen.loopgaindraft.service.media.MediaService;
import nl.tomjansen.loopgaindraft.service.media.MediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class MediaController {

    @Autowired private MediaRepository mediaRepository;
    @Autowired private MediaServiceImpl mediaService;

    // POST ONE MEDIA FILE
    @RequestMapping(value = "/projects/media", method = RequestMethod.POST)
    public ResponseEntity<Object> saveMedia(
            @RequestParam String fileName,
            @RequestParam MultipartFile file
//            ,@PathVariable Long projectId
        )
            throws IOException {

        if (mediaRepository.existsByFileName(fileName)) {
            throw new MediaAlreadyExistsException();
        } else {
            Long id = mediaService.saveMedia(fileName, file);
            return new ResponseEntity<>("Media saved with ID: " + id, HttpStatus.CREATED);
        }
    }

    // GET ONE FILE
    @RequestMapping(value = "/projects/media/{mediaId}")
    public ResponseEntity<Object> getMedia(@PathVariable Long mediaId) {
        MediaDto mediaDto = mediaService.getMedia(mediaId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(mediaDto.getContentLength());
        headers.set("Content-Type", mediaDto.getContentMimeType());
        headers.set("File-Name", mediaDto.getFileName());

        return new ResponseEntity<>(mediaDto.getInputStreamResource(), headers, HttpStatus.OK);
    }
}
