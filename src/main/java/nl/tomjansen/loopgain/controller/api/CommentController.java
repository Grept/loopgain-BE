package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.service.feedback.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/media/{mediaId}")
public class CommentController {

    private final CommentService commentService;

    @DeleteMapping("/comment")
    public ResponseEntity<Object> deleteComment(@RequestBody CommentDto commentDto, @PathVariable Long mediaId) {

        commentService.deleteComment(commentDto, mediaId);

        return new ResponseEntity<>("Deleted a comment.",
                HttpStatus.OK);
    }
}
