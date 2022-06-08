package nl.tomjansen.loopgain.controller.api;

import com.google.gson.Gson;
import nl.tomjansen.loopgain.config.TestConfig;
import nl.tomjansen.loopgain.dto.model.feedback.CommentDto;
import nl.tomjansen.loopgain.dto.model.feedback.FeedbackStringDto;
import nl.tomjansen.loopgain.dto.model.media.MediaDto;
import nl.tomjansen.loopgain.service.feedback.FeedbackStringService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FeedbackStringController.class)

@ContextConfiguration(classes = {TestConfig.class})

@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "testuser", password = "123pass", authorities = "REVIEWER")
class FeedbackStringControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackStringService feedbackStringService;

    private FeedbackStringDto feedbackStringDto;
    private final List<CommentDto> commentDtoList = new ArrayList<>();

    private final Gson gson = new Gson();


    @BeforeEach
    void setUp() {
        commentDtoList.add(
                new CommentDto()
                        .setCommentText("Comment 1")
                        .setTimeStamp(1000D));
        commentDtoList.add(
                new CommentDto()
                        .setCommentText("Comment 2")
                        .setTimeStamp(2000D));

        feedbackStringDto = new FeedbackStringDto()
                .setId(1L)
                .setCommentList(commentDtoList)
                .setMediaDto(new MediaDto().setFileName("Media File 1"))
                .setReviewer("testuser");
    }

    @Test
    @DisplayName("Testing getFeedbackString(). Should return a feedbackStringDto & 200")
    void getFeedbackString() throws Exception {
        Mockito.when(feedbackStringService.getUserFeedbackString(any())).thenReturn(feedbackStringDto);

        mockMvc
                .perform(get("/media/{mediaId}/feedback", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.commentList", hasSize(2)))
                .andExpect(jsonPath("$.commentList[0].commentText", is("Comment 1")))
                .andExpect(jsonPath("$.mediaDto.fileName", is("Media File 1")))
                .andExpect(jsonPath("$.reviewer", is("testuser")));
    }

    @Test
    void createFilledFeedbackString() throws Exception {
        Mockito.when(feedbackStringService.createFilledFeedbackString(any(), any())).thenReturn(feedbackStringDto.getId());

        mockMvc
                .perform(post("/media/{mediaId}/feedback", 1)
                        .content(gson.toJson(commentDtoList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("FeedbackString created with ID: " + 1L)));


    }

    @Test
    void deleteFeedbackString() throws Exception {
        Mockito.when(feedbackStringService.deleteFeedbackString(any())).thenReturn(1L);

        mockMvc
                .perform(delete("/feedback/{feedbackStringId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Deleted feedbackString with ID: " + 1)));
    }
}