package nl.tomjansen.loopgain.controller.api;

import nl.tomjansen.loopgain.config.TestConfig;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.service.media.MediaService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import java.io.ByteArrayInputStream;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MediaController.class)
@WithMockUser(username = "testuser", password = "123pass", authorities = "PROJECT_HOST")
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = TestConfig.class)
class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MediaService mediaService;

    private MediaDto mediaDto_1;
    private MediaDto mediaDto_2;

    @BeforeEach
    void setUp() {
        mediaDto_1 = new MediaDto()
                .setId(1L)
                .setFileName("Media File 1")
                .setContentLength(1000L)
                .setContentMimeType("video/mpeg")
                .setParentProjectName("Project 1")
                .setDirector("Director 1")
                .setProducer("Producer 1")
                .setProjectHost("testuser")
                .setInputStreamResource(new InputStreamResource(new ByteArrayInputStream(new byte[10])));

        mediaDto_2 = new MediaDto()
                .setId(2L)
                .setFileName("Media File 2")
                .setContentLength(1000L)
                .setContentMimeType("video/mpeg")
                .setParentProjectName("Project 2")
                .setDirector("Director 2")
                .setProducer("Producer 2")
                .setProjectHost("testuser")
                .setInputStreamResource(new InputStreamResource(new ByteArrayInputStream(new byte[15])));

    }

    @Test
    @DisplayName("Testing MediaController.saveMedia(). Should return RE with message containing ID and status 201 CREATED.")
    void saveMediaTest() throws Exception {
        // Mocking the multipart file (video / audio)
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "video.mp4",
                "video/mpeg",
                new byte[10]
        );

        Mockito.when(mediaService.saveMedia(file.getName(), file,1L))
                        .thenReturn(1L);

        mockMvc
                .perform(multipart("/project/{projectId}/media", 1)
                        .file(file)
                        .param("fileName", file.getName()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content()
                        .string(containsString("Media saved with ID: " + 1)));


    }

    @Test
    @DisplayName("Testing MediaController.getMediaStream(). Should return RE with mediastream, correct headers and status 200 OK.")
    void getMediaStreamTest() throws Exception {
        Mockito.when(mediaService.getMedia(any())).thenReturn(mediaDto_1);

        mockMvc
                .perform(get("/media/{mediaId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Length", "1000"))
                .andExpect(header().string("Content-Type", "video/mpeg"))
                .andExpect(header().string("File-Name", "Media File 1"))
                .andExpect(content().bytes(new byte[10]));
    }

    @Test
    @DisplayName("Testing MediaController.getMediaData(). Should return RE with MediaDto and status 200 OK.")
    void getMediaDataTest() throws Exception {
        Mockito.when(mediaService.getMedia(any())).thenReturn(mediaDto_1);

        mockMvc
                .perform(get("/media/{mediaId}/data", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Testing MediaController.getAllMediaData(). Should return RE with a list of MediaDto's and status 200 OK.")
    void getAllMediaDataTest() throws Exception {
        // mediaService.getAllMediaInfo() produces mediaDto's without an inputStreamResource.
        mediaDto_1.setInputStreamResource(null);
        mediaDto_2.setInputStreamResource(null);

        Mockito.when(mediaService.getAllMediaInfo())
                .thenReturn(Lists.newArrayList(mediaDto_1, mediaDto_2));

        mockMvc
                .perform(get("/media")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].fileName", is("Media File 1")))
                .andExpect(jsonPath("$[0].parentProjectName", is("Project 1")))
                .andExpect(jsonPath("$[0].director", is("Director 1")))
                .andExpect(jsonPath("$[0].producer", is("Producer 1")))
                .andExpect(jsonPath("$[0].projectHost", is("testuser")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].fileName", is("Media File 2")))
                .andExpect(jsonPath("$[1].parentProjectName", is("Project 2")))
                .andExpect(jsonPath("$[1].director", is("Director 2")))
                .andExpect(jsonPath("$[1].producer", is("Producer 2")))
                .andExpect(jsonPath("$[1].projectHost", is("testuser")));;
    }

    @Test
    @DisplayName("Testing MediaController.deleteMediaFile(). Should return RE correct message and status 200 OK.")
    void deleteMediaFileTest() throws Exception {
        Mockito.when(mediaService.deleteFile(any())).thenReturn(mediaDto_1);

        mockMvc
                .perform(delete("/media/{mediaId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Mediafile with name \"Media File 1\" has been deleted.")));
    }
}