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

    @RequestMapping("/board/openBoardList.do")
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("/board/boardList"); // templates/board/boardList.html

        List<BoardDto> list = boardService.selectBoardList();
        mv.addObject("list", list); // 비즈니스 로직 결과 값을 뷰에 list 라는 이름으로 저장

        return mv;
    }
}
