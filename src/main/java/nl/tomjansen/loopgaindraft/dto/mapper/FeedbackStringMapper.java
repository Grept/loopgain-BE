package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgaindraft.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgaindraft.model.feedback.Comment;
import nl.tomjansen.loopgaindraft.model.feedback.FeedbackString;

import java.util.ArrayList;
import java.util.List;

public abstract class FeedbackStringMapper {

    public static FeedbackStringDto entityToDto(FeedbackString feedbackString) {
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (Comment c : feedbackString.getCommentsList()) {
            commentDtoList.add(CommentMapper.entityToDto(c));
        }

        return new FeedbackStringDto()
                .setCommentList(commentDtoList)
                .setMediaFile(feedbackString.getMediaFile());
    }
}
