package com.insight.springstartboard.board.service;

import com.insight.springstartboard.board.dto.BoardDto;
import com.insight.springstartboard.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;    // 데이터베이스에 접근하는 DAO 빈 선언


    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }
}
