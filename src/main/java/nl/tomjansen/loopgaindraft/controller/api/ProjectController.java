package nl.tomjansen.loopgaindraft.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getProject() {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createProject() {
        return null;
    }

    @PutMapping
    public ResponseEntity<?> updateProject() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProject() {
        return null;
    }
}
