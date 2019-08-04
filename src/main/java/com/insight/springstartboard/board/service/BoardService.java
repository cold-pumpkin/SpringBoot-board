package com.insight.springstartboard.board.service;

import com.insight.springstartboard.board.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> selectBoardList() throws Exception;
}
