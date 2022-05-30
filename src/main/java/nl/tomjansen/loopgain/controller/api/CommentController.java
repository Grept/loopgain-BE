//package nl.tomjansen.loopgain.controller.api;
//
//import lombok.RequiredArgsConstructor;
//import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
//import nl.tomjansen.loopgain.service.feedback.CommentService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/feedback/{feedbackStringId}")
//public class CommentController {
//
//    private final CommentService commentService;
//
//    @PostMapping("/comment")
//    public ResponseEntity<Object> saveComment(@RequestBody CommentDto commentDto, @PathVariable Long feedbackStringId) {
//
//        commentService.saveComment(commentDto, feedbackStringId);
//
//        return new ResponseEntity<>("Added a comment to feedbackString with ID: " + feedbackStringId,
//                HttpStatus.CREATED);
//    }
//}
