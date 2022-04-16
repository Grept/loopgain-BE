package nl.tomjansen.loopgain.repository.media;

import nl.tomjansen.loopgain.model.media.Media;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.stereotype.Component;

@Component // Annotation to make sure intelij does not complain about a missing bean
public interface MediaContentStore extends ContentStore<Media, String> {
}
