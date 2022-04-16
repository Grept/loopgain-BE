package nl.tomjansen.loopgain.repository.project;

import nl.tomjansen.loopgain.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
