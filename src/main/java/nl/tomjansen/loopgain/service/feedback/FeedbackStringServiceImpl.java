package nl.tomjansen.loopgain.service.feedback;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.dto.mapper.FeedbackStringMapper;
import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.exception.RecordNotFoundException;
import nl.tomjansen.loopgain.model.feedback.Comment;
import nl.tomjansen.loopgain.model.feedback.FeedbackString;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.feedback.FeedbackStringRepository;
import nl.tomjansen.loopgain.repository.media.MediaRepository;
import nl.tomjansen.loopgain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackStringServiceImpl implements FeedbackStringService{

    private final FeedbackStringRepository feedbackStringRepository;
    private final MediaRepository mediaRepository;
    @Lazy @Autowired private CommentService commentService;
    private final UserRepository userRepository;

    @Override
    public FeedbackStringDto getUserFeedbackString(Long mediaId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        Optional<FeedbackString> feedbackStringOptional = feedbackStringRepository.findFeedbackStringByMediaFileIdAndReviewerUsername(mediaId, userDetails.getUsername());

        if(feedbackStringOptional.isPresent()) {
            return FeedbackStringMapper.entityToDto(feedbackStringOptional.get());
        } else {
            throw new RecordNotFoundException(String.format("FeedbackString from user %s for mediafile with ID: %d, was not found.", userDetails.getUsername(), mediaId));
        }
    }

    @Override
    public Long createFeedbackString(Long mediaId) {
        Optional<Media> mediaOptional = mediaRepository.findById(mediaId);

        if(mediaOptional.isPresent()) {
            FeedbackString feedbackString = new FeedbackString();
            feedbackString.setMediaFile(mediaOptional.get());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            Optional<User> userOptional = userRepository.findUserByUsername(userDetails.getUsername());
            if(userOptional.isPresent()) {
                feedbackString.setReviewer(userOptional.get());
            }

            return feedbackStringRepository.save(feedbackString).getId();
        } else {
            throw new RecordNotFoundException(String.format("Mediafile with ID: %d was not found.", mediaId));
        }

    }

    @Override
    public Long createFilledFeedbackString(Long mediaId, List<CommentDto> commentList) {
        // Check if MediaFile already has a feedbackstring from this user.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        Optional<FeedbackString> feedbackStringOptional = feedbackStringRepository.findFeedbackStringByMediaFileIdAndReviewerUsername(mediaId, userDetails.getUsername());
        Long feedbackStringId;
        if(feedbackStringOptional.isPresent()) {
            feedbackStringId = feedbackStringOptional.get().getId();
        } else {
            feedbackStringId = createFeedbackString(mediaId);
        }


        for(CommentDto commentDto : commentList) {
            commentService.saveComment(commentDto, feedbackStringId);
        }

        return feedbackStringId;
    }

    @Override
    public Long deleteFeedbackString(Long feedbackStringId) {
        Optional<FeedbackString> feedbackString = feedbackStringRepository.findById(feedbackStringId);
        if (feedbackString.isPresent()) {
            feedbackStringRepository.delete(feedbackString.get());
            return feedbackStringId;
        } else {
            throw new RecordNotFoundException(String.format("FeedbackString with ID: %d was not found.", feedbackStringId));
        }
    }

    @Override
    public void deleteCommentFromFeedbackString(Comment comment, Long feedbackStringId) {
        Optional<FeedbackString> feedbackStringOptional = feedbackStringRepository.findById(feedbackStringId);

        if(feedbackStringOptional.isPresent()) {
            FeedbackString feedbackString = feedbackStringOptional.get();
            feedbackString.removeComment(comment);
            feedbackStringRepository.save(feedbackString);
        } else {
            throw new RecordNotFoundException("Could not find feedbackstring with ID: " + feedbackStringId);
        }
    }
}
