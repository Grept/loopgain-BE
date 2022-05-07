package nl.tomjansen.loopgain.service.media;

import nl.tomjansen.loopgain.model.media.Media;
import nl.tomjansen.loopgain.model.project.Project;
import nl.tomjansen.loopgain.model.user.User;
import nl.tomjansen.loopgain.repository.media.MediaContentStore;
import nl.tomjansen.loopgain.repository.media.MediaRepository;
import nl.tomjansen.loopgain.repository.project.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Primary;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
    private Media media;

    @BeforeEach
    void setUp() {
        media = new Media();
        media.setId(1L);

        project = new Project()
                .setId(1L)
                .setProjectName("Project 1")
                .setDirector("Director 1")
                .setProducer("Producer 1")
                .setProjectOwner(user);
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
                .when(mediaRepository.save(any())).thenReturn(media);


        Long mediaId = mediaService.saveMedia(filename, file, projectId);

        assertEquals(1, mediaId);
    }

    @Test
    void getMedia() {
    }

    @Test
    void getAllMediaInfo() {
    }

    @Test
    void deleteFile() {
    }
}