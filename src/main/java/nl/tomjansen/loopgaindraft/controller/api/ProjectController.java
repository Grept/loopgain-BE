package nl.tomjansen.loopgaindraft.controller.api;

import nl.tomjansen.loopgaindraft.controller.request.ProjectRequest;
import nl.tomjansen.loopgaindraft.dto.mapper.ProjectMapper;
import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<?> getAllProjects() {
        return new ResponseEntity<>("getAllProjects()", HttpStatus.OK);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<?> getProject() {
        return new ResponseEntity<>("getProject()", HttpStatus.OK);
    }


    // ADD a project to the db.
    @PostMapping("/projects")
    public ResponseEntity<Object> createProject(@Valid @RequestBody ProjectRequest projectRequest, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for(FieldError fe: br.getFieldErrors()) {
                sb.append(fe).append("/n");
            }

            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        ProjectDto projectDto = ProjectMapper.requestToDto(projectRequest);
        Long projectId = projectService.postProject(projectDto);

        return new ResponseEntity<>("Project created with ID#: " + projectId, HttpStatus.CREATED);
    }

    @PutMapping("/projects")
    public ResponseEntity<?> updateProject() {
        return null;
    }

    @DeleteMapping("/projects")
    public ResponseEntity<?> deleteProject() {
        return null;
    }
}
