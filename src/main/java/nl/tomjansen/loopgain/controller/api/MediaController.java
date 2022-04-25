package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.repository.media.MediaRepository;
import nl.tomjansen.loopgain.service.media.MediaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000/")
public class MediaController {

    // Dependency Injection via @RequiredArgsConstructor
    private final MediaRepository mediaRepository;
    private final MediaService mediaService;

    // POST ONE MEDIA FILE
    @RequestMapping(value = "/project/{projectId}/media", method = RequestMethod.POST)
    public ResponseEntity<Object> saveMedia(
            @RequestParam MultipartFile file,
            @RequestParam String fileName,
            @PathVariable Long projectId)
            throws IOException {

            Long id = mediaService.saveMedia(fileName, file, projectId);
            return new ResponseEntity<>("Media saved with ID: " + id, HttpStatus.CREATED);

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
