package nl.tomjansen.loopgain.repository.project;

import nl.tomjansen.loopgain.model.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByProjectOwner_Id(Long id);
}
