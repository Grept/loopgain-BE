//package nl.tomjansen.loopgaindraft.controller.api;
//
//import nl.tomjansen.loopgaindraft.service.media.VideoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//public class VideoController {
//
//    @Autowired
//    private VideoService videoService;
//
//    // Add a video to a project
//    @PostMapping(value = "/media")
//    public ResponseEntity<Object> postVideo(@RequestParam MultipartFile file, @RequestParam String name) throws IOException {
//
//        videoService.saveVideo(file, name);
//        return new ResponseEntity<>("Video uploaded.", HttpStatus.CREATED);
//    }
//
//    @GetMapping("/media/{name}")
//    public ResponseEntity<Object> getVideo(@PathVariable String name) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE));
//
//        return new ResponseEntity<>(new ByteArrayResource(videoService.getVideo(name).getData()), headers, HttpStatus.OK);
//    }
//
//}
