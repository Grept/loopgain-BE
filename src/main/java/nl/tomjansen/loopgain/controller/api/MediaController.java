package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.repository.media.MediaRepository;
import nl.tomjansen.loopgain.service.media.MediaService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000/")
public class MediaController {

    // Dependency Injection via @RequiredArgsConstructor
    private final MediaService mediaService;

    // POST ONE MEDIA FILE
    @RequestMapping(value = "/project/{projectId}/media", method = RequestMethod.POST)
    public ResponseEntity<Object> saveMedia(
            @RequestParam MultipartFile file,
            @RequestParam String fileName,
            @PathVariable Long projectId)
            throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails ud = (UserDetails) auth.getPrincipal();
        System.out.println("Media uploaded by: " + ud.getUsername());

        Long id = mediaService.saveMedia(fileName, file, projectId);
        return new ResponseEntity<>("Media saved with ID: " + id, HttpStatus.CREATED);
    }

    // GET ONE FILESTREAM
    @RequestMapping(value = "/media/{mediaId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMediaStream(@PathVariable Long mediaId) {
        MediaDto mediaDto = mediaService.getMedia(mediaId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(mediaDto.getContentLength());
        headers.set("Content-Type", mediaDto.getContentMimeType());
        headers.set("File-Name", mediaDto.getFileName());

        return new ResponseEntity<>(mediaDto.getInputStreamResource(), headers, HttpStatus.OK);
    }

    // GET FILE METADATA
    @RequestMapping(value = "/media/{mediaId}/data", method = RequestMethod.GET)
    public ResponseEntity<Object> getMediaData(@PathVariable Long mediaId) {
        MediaDto mediaDto = mediaService.getMedia(mediaId);
        mediaDto.setInputStreamResource(null);

        return new ResponseEntity<>(mediaDto, HttpStatus.OK);
    }


    // GET ALL MEDIAINFO
    @RequestMapping(value = "/media")
    public ResponseEntity<Object> getAllMediaData() {
        return new ResponseEntity<>(mediaService.getAllMediaInfo(), HttpStatus.OK);
    }

    // DELETE MEDIA FILE
    @RequestMapping(value = "/media/{mediaId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteMediaFile(@PathVariable Long mediaId) {

        MediaDto mediaDto = mediaService.deleteFile(mediaId);

        return new ResponseEntity<>(String.format(
                "Mediafile with name \"%s\" has been deleted.",
                mediaDto.getFileName()
        ), HttpStatus.OK);
    }
}
