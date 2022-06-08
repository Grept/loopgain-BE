package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.service.feedback.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping("/media/{mediaId}")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<Object> getCommentId(@RequestBody CommentDto commentDto, @PathVariable Long mediaId) {

        Long commentId = commentService.getCommentId(commentDto, mediaId);

        return new ResponseEntity<>(commentId, HttpStatus.FOUND);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long mediaId, @PathVariable Long commentId) {

        commentService.deleteComment(mediaId, commentId);

        return new ResponseEntity<>("Deleted a comment.",
                HttpStatus.OK);
    }
}
