package com.insight.springstartboard.board.controller;

import com.insight.springstartboard.board.dto.BoardDto;
import com.insight.springstartboard.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 목록 조회
    @RequestMapping("/board/openBoardList.do")
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("/board/boardList"); // templates/board/boardList.html

        List<BoardDto> list = boardService.selectBoardList();
        mv.addObject("list", list); // 비즈니스 로직 결과 값을 뷰에 list 라는 이름으로 저장

        return mv;
    }

    // 게시글 작성 화면 호출
    @RequestMapping("/board/openBoardWriter.do")
    public String openBoardWriter() throws Exception {
        return "/board/boardWrite";
    }

    // 작성된 게시글 등록
    @RequestMapping("/board/insertBoard.do")
    public String insertBoard(BoardDto board) throws Exception {
        boardService.insertBoard(board);
        return "redirect:/board/openBoardList.do";  // 게시글 작성 후 게시글 목록 화면으로 이동
    }
}