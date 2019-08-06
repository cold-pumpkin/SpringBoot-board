package com.insight.springstartboard.board.mapper;

import com.insight.springstartboard.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto> selectBoardList() throws Exception;

    void insertBoard(BoardDto board) throws Exception;

    void updateHitCount(int boardIdx);

    BoardDto selectBoardDetail(int boardIdx);

    void updateBoard(BoardDto board);

    void deleteBoard(int boardIdx);
}
