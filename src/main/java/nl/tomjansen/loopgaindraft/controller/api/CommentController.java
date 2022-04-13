package nl.tomjansen.loopgaindraft.controller.api;

import nl.tomjansen.loopgaindraft.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgaindraft.service.feedback.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback/{feedbackStringId}")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<Object> saveComment(@RequestBody CommentDto commentDto, @PathVariable Long feedbackStringId) {

        commentService.saveComment(commentDto, feedbackStringId);

        return new ResponseEntity<>("Added a comment to feedbackString with ID: " + feedbackStringId,
                HttpStatus.CREATED);
    }
}
