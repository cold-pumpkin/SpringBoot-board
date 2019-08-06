package com.insight.springstartboard.board.service;

import com.insight.springstartboard.board.dto.BoardDto;

import java.util.List;

public interface BoardService {

    List<BoardDto> selectBoardList() throws Exception;

    void insertBoard(BoardDto board) throws Exception;

    BoardDto selectBoardDetail(int boardIdx) throws Exception;

    void updateBoard(BoardDto board);

    void deleteBoard(int boardIdx);
}

