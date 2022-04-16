package nl.tomjansen.loopgain.repository.media;

import nl.tomjansen.loopgain.model.media.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Boolean existsByFileName(String fileName);
}
