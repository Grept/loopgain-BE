package nl.tomjansen.loopgain.dto.mapper;

import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.dto.model.project.ProjectDto;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.model.project.Project;
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
                .setProjectOwner(entity.getProjectOwner().getUsername())
                .setProjectMedia(mediaDtoList);
    }

    public static Project dtoToEntity(ProjectDto projectDto) {
        return new Project()
                .setProjectName(projectDto.getProjectName())
                .setDirector(projectDto.getDirector())
                .setProducer(projectDto.getProducer());
    }
}
