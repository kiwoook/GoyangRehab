package com.study.goyangrehab.domain.board.controller;

import com.study.goyangrehab.config.JpaAuditingConfiguration;
import com.study.goyangrehab.domain.board.entity.Board;
import com.study.goyangrehab.domain.board.entity.boards.Free;
import com.study.goyangrehab.domain.board.entity.boards.JobPosting;
import com.study.goyangrehab.domain.board.entity.boards.QnA;
import com.study.goyangrehab.domain.board.repository.BoardRepository;
import com.study.goyangrehab.enums.BoardCategory;
import com.study.goyangrehab.enums.SearchType;
import com.study.goyangrehab.domain.board.service.impl.BoardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
@MockBean(JpaMetamodelMappingContext.class)
class BoardControllerTest {

    @MockBean
    BoardServiceImpl boardService;

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


        boardRepository.save(new Free(new Board("제목1", "내용1", "글쓴이1", 0, null)));
        boardRepository.save(new Free(new Board("제목2", "내용2", "글쓴이2", 0, null)));
        boardRepository.save(new JobPosting(new Board("제목1", "내용1", "글쓴이1", 0, null)));
        boardRepository.save(new JobPosting(new Board("제목2", "내용2", "글쓴이2", 0, null)));
        boardRepository.save(new QnA(new Board("제목1", "내용1", "글쓴이1", 0, null)));
        boardRepository.save(new QnA(new Board("제목2", "내용2", "글쓴이2", 0, null)));
    }

    @Test
    @DisplayName("카테고리별:Free 검색 테스트")
    void searchCategory1() throws Exception {
        // given
        BoardCategory category = BoardCategory.FREE;
        SearchType type = SearchType.TITLE;
        String query = "제목";

        // when
        mockMvc.perform(get("/api/board/search")
                        .param("page", String.valueOf(1))
                        .param("category", String.valueOf(category))
                        .param("type", String.valueOf(type))
                        .param("query", query))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("카테고리별:JobPosting 검색 테스트")
    void searchCategory2() throws Exception {
        BoardCategory category = BoardCategory.JOB_POSTING;
        SearchType type = SearchType.TITLE;
        String query = "제목";

        // when
        mockMvc.perform(get("/api/board/search")
                        .param("page", String.valueOf(1))
                        .param("category", String.valueOf(category))
                        .param("type", String.valueOf(type))
                        .param("query", query))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("카테고리별:QnA 검색 테스트")
    void searchCategory3() throws Exception {
        BoardCategory category = BoardCategory.QNA;
        SearchType type = SearchType.TITLE;
        String query = "제목";

        // when
        mockMvc.perform(get("/api/board/search")
                        .param("page", String.valueOf(1))
                        .param("category", String.valueOf(category))
                        .param("type", String.valueOf(type))
                        .param("query", query))
                .andExpect(status().isOk()).andDo(print());
    }

}
