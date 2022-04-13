package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgaindraft.model.feedback.Comment;

public abstract class CommentMapper {

    public static Comment dtoToEntity(CommentDto dto) {
        return new Comment(dto.getCommentText());
    }

    public static CommentDto entityToDto(Comment entity) {
        return new CommentDto()
                .setCommentText(entity.getCommentText())
                .setTimeStamp(entity.getTimeStamp());
    }
}
