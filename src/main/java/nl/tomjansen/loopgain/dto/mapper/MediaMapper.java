package nl.tomjansen.loopgain.dto.mapper;

import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.model.feedback.FeedbackString;
import nl.tomjansen.loopgain.model.media.Media;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

public abstract class MediaMapper {

    public static Media mpfToEntity(String fileName, MultipartFile file) {
        Media mediaFile = new Media();

        mediaFile.setFileName(fileName);
        mediaFile.setContentMimeType(file.getContentType());

        return mediaFile;
    }

    public static MediaDto entityToDto(Media entity, InputStreamResource inputStreamResource) {
        /*
        * The inputStreamResource is passed to the mapper method as an argument. This way I keep external control over
        * whether or not the Media DTO gets an inputStream or not. Sometimes I just want to send metadata, and not the
        * stream, in those cases I can pass in null; when I do want a stream in the DTO we provide it as an argument.
        */
        List<FeedbackStringDto> feedbackStringDtoList = new ArrayList<>();

        for (FeedbackString feedbackString : entity.getFeedbackCollection()) {
            feedbackStringDtoList.add(FeedbackStringMapper.entityToDto(feedbackString));
        }

        return new MediaDto()
                .setId(entity.getId())
                .setFileName(entity.getFileName())
                .setCreationDateTime(entity.getCreationDateTime())
                .setContentMimeType(entity.getContentMimeType())
                .setContentId(entity.getContentId())
                .setContentLength(entity.getContentLength())
                .setParentProjectName(entity.getProject().getProjectName())
                .setDirector(entity.getProject().getDirector())
                .setProducer(entity.getProject().getProducer())
                .setProjectHost(entity.getProject().getProjectOwner().getUsername())
                .setFeedbackStringDtoList(feedbackStringDtoList)
                .setInputStreamResource(inputStreamResource);
    }
}
