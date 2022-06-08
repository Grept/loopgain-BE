package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.service.feedback.FeedbackStringService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000/")
public class FeedbackStringController {

    private final FeedbackStringService feedbackStringService;

    @GetMapping("/media/{mediaId}/feedback")
    public ResponseEntity<Object> getFeedbackString(@PathVariable Long mediaId) {

        FeedbackStringDto feedbackStringDto = feedbackStringService.getUserFeedbackString(mediaId);

        return new ResponseEntity<>(feedbackStringDto, HttpStatus.OK);
    }

    @PostMapping("/media/{mediaId}/feedback")
    public ResponseEntity<Object> createFilledFeedbackString(
            @PathVariable Long mediaId,
            @RequestBody List<CommentDto> commentList) {

        Long feedbackStringId = feedbackStringService.createFilledFeedbackString(mediaId, commentList);

        return new ResponseEntity<>("FeedbackString created with ID: " + feedbackStringId, HttpStatus.CREATED);
    }

    @DeleteMapping("/feedback/{feedbackStringId}")
    public ResponseEntity<Object> deleteFeedbackString(@PathVariable Long feedbackStringId) {
        return new ResponseEntity<>("Deleted feedbackString with ID: " +
                feedbackStringService.deleteFeedbackString(feedbackStringId),
                HttpStatus.OK);
    }
}
