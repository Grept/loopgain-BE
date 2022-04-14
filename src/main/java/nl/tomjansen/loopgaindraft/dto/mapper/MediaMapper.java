package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.dto.model.media.MediaDto;
import nl.tomjansen.loopgaindraft.model.media.Media;
import nl.tomjansen.loopgaindraft.repository.media.MediaContentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

public abstract class MediaMapper {

    public static Media mpfToEntity(String fileName, MultipartFile file) {
        Media mediaFile = new Media();

        mediaFile.setFileName(fileName);
        mediaFile.setContentMimeType(file.getContentType());

        return mediaFile;
    }

    public static MediaDto entityToDto(Media entity, InputStreamResource inputStreamResource) {

        // inputStreamResource wordt hier als argument meegegeven aan de methode in plaats van opgehaald via het
        // media object. De inputStreamResource wordt opgebouwd met behulp van de contentStore.
        // Via dependency injection zouden we deze contentStore de klasse in injecteren maar omdat deze methode static
        // is zou ook de contentStore static moeten zijn als we hem willen gebruiken.
        // Een static field injecteren gaat een beetje voorbij aan het idee van dependency injection. Daarom geven we
        // de inputStreamResource mee als argument, dan hoeven we geen contentStore te Autowiren.

        return new MediaDto()
                .setId(entity.getId())
                .setFileName(entity.getFileName())
                .setCreationDateTime(entity.getCreationDateTime())
                .setContentMimeType(entity.getContentMimeType())
                .setContentId(entity.getContentId())
                .setContentLength(entity.getContentLength())
                .setInputStreamResource(inputStreamResource);

    }
}
