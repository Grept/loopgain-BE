package nl.tomjansen.loopgaindraft.repository.media;

import nl.tomjansen.loopgaindraft.model.media.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
