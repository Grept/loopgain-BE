package nl.tomjansen.loopgain.service.media;

import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.media.MediaContentStore;
import nl.tomjansen.loopgain.repository.media.MediaRepository;
import nl.tomjansen.loopgain.repository.project.ProjectRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@WithMockUser(username = "testuser", password = "123pass", authorities = "PROJECT_HOST")
class MediaServiceTest {

    @Autowired
    private MediaService mediaService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private MediaRepository mediaRepository;

    @Mock
    private MediaContentStore mediaContentStore;

    private User user;
    private Project project;
    private Media media_1;
    private Media media_2;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUsername("testuser");

        project = new Project()
                .setId(1L)
                .setProjectName("Project 1")
                .setDirector("Director 1")
                .setProducer("Producer 1")
                .setProjectOwner(user);

        media_1 = new Media();
        media_1.setId(1L);
        media_1.setFileName("Media File 1");
        media_1.setProject(project);

        media_2 = new Media();
        media_2.setId(2L);
        media_2.setFileName("Media File 2");
        media_2.setProject(project);
    }

    @Test
    void saveMedia() throws IOException {
        String filename = "Media file 1";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "video.mp4",
                "video/mpeg",
                new byte[10]);
        Long projectId = 1L;

        Mockito
                .when(projectRepository.findById(any()))
                .thenReturn(Optional.of(project));
        Mockito
                .when(mediaRepository.existsByFileName(filename))
                .thenReturn(false);
        Mockito
                .when(mediaContentStore.setContent(any(), (InputStream) any()))
                .thenReturn(null);
        Mockito
                .when(mediaRepository.save(any())).thenReturn(media_1);


        Long mediaId = mediaService.saveMedia(filename, file, projectId);

        assertEquals(1, mediaId);
    }

    @Test
    void getAllMediaInfo() {
        Mockito
                .when(mediaRepository.findAll())
                .thenReturn(Lists.newArrayList(media_1, media_2));

        List<MediaDto> mediaDtoList = mediaService.getAllMediaInfo();

        assertEquals(2, mediaDtoList.size());
        assertEquals(1, mediaDtoList.get(0).getId());
        assertEquals("Media File 1", mediaDtoList.get(0).getFileName());
        assertEquals("Project 1", mediaDtoList.get(0).getParentProjectName());
        assertEquals(2, mediaDtoList.get(1).getId());
        assertEquals("Media File 2", mediaDtoList.get(1).getFileName());
        assertEquals("Project 1", mediaDtoList.get(1).getParentProjectName());

    }
}