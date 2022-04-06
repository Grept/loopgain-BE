package nl.tomjansen.loopgaindraft.repository.project;

import nl.tomjansen.loopgaindraft.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
