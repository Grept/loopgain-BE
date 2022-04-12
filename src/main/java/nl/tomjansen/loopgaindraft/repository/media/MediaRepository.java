package nl.tomjansen.loopgaindraft.repository.media;

import nl.tomjansen.loopgaindraft.model.media.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Boolean existsByFileName(String fileName);
}
