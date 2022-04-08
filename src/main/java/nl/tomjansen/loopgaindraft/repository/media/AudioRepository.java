package nl.tomjansen.loopgaindraft.repository.media;

import nl.tomjansen.loopgaindraft.model.media.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Long> {
}
