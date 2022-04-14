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
@RequestMapping(value = "/user")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<Object> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Object> getProject(@PathVariable Long projectId) {
        return new ResponseEntity<>(projectService.getProject(projectId), HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity<Object> createProject(@Valid @RequestBody ProjectRequest projectRequest, BindingResult br) {
        if(br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for(FieldError fe: br.getFieldErrors()) {
                sb.append(fe).append("/n");
            }

            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        Long projectId = projectService.postProject(ProjectMapper.requestToDto(projectRequest));

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
