package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.controller.request.ProjectRequest;
import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.model.project.Project;

public abstract class ProjectMapper {

    public static ProjectDto entityToDto(Project project) {
        return new ProjectDto()
                .setProjectName(project.getProjectName())
                .setDirector(project.getDirector())
                .setProducer(project.getProducer())
                .setProjectOwner(project.getProjectOwner())
                .setProjectMedia(project.getMedia());
    }

    public static ProjectDto requestToDto(ProjectRequest projectRequest) {
        return new ProjectDto()
                .setProjectName(projectRequest.getProjectName())
                .setDirector(projectRequest.getDirector())
                .setProducer(projectRequest.getProducer());
//                .setProjectOwner(new User());
    }

    public static Project dtoToEntity(ProjectDto projectDto) {
        return new Project()
                .setProjectName(projectDto.getProjectName())
                .setDirector(projectDto.getDirector())
                .setProducer(projectDto.getProducer())
                .setProjectOwner(projectDto.getProjectOwner());
    }
}
