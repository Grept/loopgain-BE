package nl.tomjansen.loopgain.dto.mapper;

import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.model.feedback.Comment;
import nl.tomjansen.loopgain.model.feedback.FeedbackString;

import java.util.ArrayList;
import java.util.List;

public abstract class FeedbackStringMapper {

    public static FeedbackStringDto entityToDto(FeedbackString feedbackString) {
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (Comment c : feedbackString.getCommentsList()) {
            commentDtoList.add(CommentMapper.entityToDto(c));
        }

        return new FeedbackStringDto()
                .setId(feedbackString.getId())
                .setCommentList(commentDtoList)
                .setReviewer(feedbackString.getReviewer().getUsername());
//                .setMediaDto(MediaMapper.entityToDto(feedbackString.getMediaFile(), null));
    }
}
