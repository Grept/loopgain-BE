package nl.tomjansen.loopgain.controller.api;

import lombok.RequiredArgsConstructor;
import nl.tomjansen.loopgain.controller.request.ProjectRequest;
import nl.tomjansen.loopgain.dto.mapper.ProjectMapper;
import nl.tomjansen.loopgain.service.project.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class ProjectController {

    // Dependency Injection via @RequiredArgsConstructor
    private final ProjectService projectService;

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
