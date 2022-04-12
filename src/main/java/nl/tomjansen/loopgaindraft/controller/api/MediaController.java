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


@RestController
@RequestMapping(value = "/projects")
public class MediaController {

    @Autowired private MediaRepository mediaRepository;
    @Autowired private MediaServiceImpl mediaService;

    // POST ONE MEDIA FILE
    @RequestMapping(value = "/media", method = RequestMethod.POST)
    public ResponseEntity<Object> saveMedia(
            @RequestParam String fileName,
            @RequestParam MultipartFile file)
            throws IOException {

        // TO DO: move this check to the service layer.
        if (mediaRepository.existsByFileName(fileName)) {
            throw new MediaAlreadyExistsException();
        } else {
            Long id = mediaService.saveMedia(fileName, file);
            return new ResponseEntity<>("Media saved with ID: " + id, HttpStatus.CREATED);
        }
    }

    // GET ONE FILE
    @RequestMapping(value = "/media/{mediaId}")
    public ResponseEntity<Object> getMedia(@PathVariable Long mediaId) {
        MediaDto mediaDto = mediaService.getMedia(mediaId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(mediaDto.getContentLength());
        headers.set("Content-Type", mediaDto.getContentMimeType());
        headers.set("File-Name", mediaDto.getFileName());

        return new ResponseEntity<>(mediaDto.getInputStreamResource(), headers, HttpStatus.OK);
    }

    // LIST ALL FILENAMES
    @RequestMapping(value = "/media")
    public ResponseEntity<Object> listAllMedia() {
        return new ResponseEntity<>(mediaService.listAllMedia(), HttpStatus.OK);
    }

    // DELETE MEDIA FILE
    @RequestMapping(value = "/media/{mediaId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteMediaFile(@PathVariable Long mediaId) {

        mediaService.deleteFile(mediaId);

        return new ResponseEntity<>("Media file deleted.", HttpStatus.OK);
    }
}
