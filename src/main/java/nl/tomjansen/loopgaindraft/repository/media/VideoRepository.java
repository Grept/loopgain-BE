package nl.tomjansen.loopgaindraft.repository.media;

import nl.tomjansen.loopgaindraft.model.media.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
