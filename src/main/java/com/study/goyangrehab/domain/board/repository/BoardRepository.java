package com.study.goyangrehab.domain.board.repository;


import com.study.goyangrehab.domain.board.entity.Board;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

//
//    - [x]  이름을 통한 게시판 작성자 검색
//- [x]  내용 검색 (QueryDSL 적용필요)
//- [x]  제목 검색 (QueryDSL 적용필요)
//- [x]  회원아이디 검색
//- [x]  제목 + 내용 검색  (QueryDSL 적용필요)
    @NonNull Optional<Board> findById(@NonNull Long id);

    Optional<List<Board>> findAllByCreatorContaining(String creator);

    Optional<List<Board>> findAllByTitleContaining(String title);

    Optional<List<Board>> findAllByContentContaining(String content);

//    Optional<List<Board>> findAllByUserId(String userId);

    Optional<List<Board>> findByTitleContainingOrContentContaining(String title, String content);



}
