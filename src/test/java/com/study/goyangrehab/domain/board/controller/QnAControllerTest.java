package com.study.goyangrehab.domain.board.controller;

import com.google.gson.Gson;
import com.study.goyangrehab.config.JpaAuditingConfiguration;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.QnA;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.domain.board.service.impl.QnAServiceImpl;
import com.study.goyangrehab.domain.board.dto.BoardAddForm;
import com.study.goyangrehab.domain.board.dto.BoardRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

@Disabled
@WebMvcTest(QnAController.class)
@MockBean(JpaMetamodelMappingContext.class)
class QnAControllerTest {

    @MockBean
    QnAServiceImpl qnAService;

    @MockBean
    JpaAuditingConfiguration jpaAuditingConfiguration;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private BoardRepository boardRepository;


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("QnA 생성 테스트")
    void createQnATest() throws Exception {

        BoardAddForm boardAddForm = BoardAddForm.builder()
                .title("제목")
                .content("내용")
                .creator("글쓴이")
                .generalFiles(null)
                .imageFiles(null)
                .build();


        Gson gson = new Gson();

        String content = gson.toJson(boardAddForm);

        mockMvc.perform(
                        post("/api/board/qna")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("QnA 업데이트 테스트")
    void updateQnATest() throws Exception {

        // given
        QnA qna1 = new QnA(new Board("제목", "내용", "글쓴이", 0, null));

        boardRepository.save(qna1);

        BoardAddForm boardAddForm = BoardAddForm.builder()
                .title("수정 제목")
                .content("수정 내용")
                .creator("수정 글쓴이")
                .generalFiles(null)
                .imageFiles(null)
                .build();

        Gson gson = new Gson();

        String content = gson.toJson(boardAddForm);

        // when
        mockMvc.perform(put("/api/board/qna/{id}", 1L).contentType(MediaType.APPLICATION_JSON).content(content)).andExpect(status().isOk()).andDo(print());


        // then
        verify(qnAService, times(1)).updateQnA(eq(1L), any(BoardRequestDto.class));

    }

}
