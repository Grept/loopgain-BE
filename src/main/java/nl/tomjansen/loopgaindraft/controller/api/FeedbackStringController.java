package nl.tomjansen.loopgaindraft.controller.api;

import nl.tomjansen.loopgaindraft.service.feedback.FeedbackStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackStringController {

    @Autowired
    public FeedbackStringService service;

    @GetMapping("/feedback/{fbsId}")
    public ResponseEntity<?> retrieveFeedbackString(@PathVariable Long fbsId) {
        return new ResponseEntity<>(service.getFeedbackString(fbsId), HttpStatus.OK);
    }
}
