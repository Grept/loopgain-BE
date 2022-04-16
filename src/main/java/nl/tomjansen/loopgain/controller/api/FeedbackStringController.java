package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.service.feedback.FeedbackStringService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeedbackStringController {

    private final FeedbackStringService feedbackStringService;

    @GetMapping("/feedback/{feedbackStringId}")
    public ResponseEntity<Object> getFeedbackString(@PathVariable Long feedbackStringId) {

        FeedbackStringDto feedbackStringDto = feedbackStringService.getFeedbackString(feedbackStringId);

        return new ResponseEntity<>(feedbackStringDto, HttpStatus.OK);
    }

    @PostMapping("/media/{mediaId}/feedback")
    public ResponseEntity<Object> createFeedbackString(@PathVariable Long mediaId) {
        Long feedbackStringId = feedbackStringService.createFeedbackString(mediaId);

        return new ResponseEntity<>("Created feedback string with ID: " + feedbackStringId,
                HttpStatus.CREATED);
    }

    @DeleteMapping("/feedback/{feedbackStringId}")
    public ResponseEntity<Object> deleteFeedbackString(@PathVariable Long feedbackStringId) {
        return new ResponseEntity<>("Deleted feedbackString with ID: " +
                feedbackStringService.deleteFeedbackString(feedbackStringId),
                HttpStatus.OK);
    }
}
