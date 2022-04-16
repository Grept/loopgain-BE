package nl.tomjansen.loopgain.dto.mapper;

import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.model.feedback.Comment;

public abstract class CommentMapper {

    public static Comment dtoToEntity(CommentDto dto) {
        return new Comment(dto.getCommentText());
    }

    public static CommentDto entityToDto(Comment entity) {
        return new CommentDto()
                .setId(entity.getId())
                .setCommentText(entity.getCommentText())
                .setTimeStamp(entity.getTimeStamp());
    }
}
