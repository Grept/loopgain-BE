package nl.tomjansen.loopgaindraft.dto.mapper;

import nl.tomjansen.loopgaindraft.controller.request.ProjectRequest;
import nl.tomjansen.loopgaindraft.dto.model.media.MediaDto;
import nl.tomjansen.loopgaindraft.dto.model.project.ProjectDto;
import nl.tomjansen.loopgaindraft.model.media.Media;
import nl.tomjansen.loopgaindraft.model.project.Project;

import java.util.ArrayList;
import java.util.List;

public abstract class ProjectMapper {

    public static ProjectDto entityToDto(Project entity) {
        List<MediaDto> mediaDtoList = new ArrayList<>();

        for (Media m : entity.getMedia()) {
            mediaDtoList.add(MediaMapper.entityToDto(m, null));
        }

        return new ProjectDto()
                .setId(entity.getId())
                .setProjectName(entity.getProjectName())
                .setDirector(entity.getDirector())
                .setProducer(entity.getProducer())
                .setProjectOwner(entity.getProjectOwner())
                .setProjectMedia(mediaDtoList);
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
