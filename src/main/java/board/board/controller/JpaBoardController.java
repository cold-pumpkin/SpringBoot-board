package board.board.controller;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;
import board.board.service.JpaBoardService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class JpaBoardController {

    @Autowired
    private JpaBoardService jpaBoardService;

    // 게시글 목록
    @RequestMapping(value="/jpa/board", method = RequestMethod.GET)
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("/board/jpaBoardList");

        List<BoardEntity> list = jpaBoardService.selectBoardList();
        mv.addObject("list", list);

        return mv;
    }

    // 게시글 작성 화면
    @RequestMapping(value = "/jpa/board/write", method = RequestMethod.GET)
    public String openBoardWrite() throws  Exception {
        return "/board/jpaBoardWrite";
    }

    // 게시글 작성
    @RequestMapping(value = "/jpa/board/write", method = RequestMethod.POST)
    public String writeBoard(BoardEntity board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
        jpaBoardService.saveBoard(board, multipartHttpServletRequest);
        return "redirect:/jpa/board";
    }

    // 게시글 상세 화면
    @RequestMapping(value = "/jpa/board/{boardIdx}", method = RequestMethod.GET)
    public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
        ModelAndView mv = new ModelAndView("/board/jpaBoardDetail");

        BoardEntity board = jpaBoardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board);

        return mv;
    }

    // 게시글 수정
    @RequestMapping(value = "/jpa/board/{boardIdx}", method = RequestMethod.PUT)
    public String updateBoard(BoardEntity board) throws Exception{
        jpaBoardService.saveBoard(board, null);
        return "redirect:/jpa/board";
    }

    // 게시글 삭제
    @RequestMapping(value = "/jpa/board/{boardIdx}", method = RequestMethod.DELETE)
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception{
        jpaBoardService.deleteBoard(boardIdx);
        return "redirect:/jpa/board";
    }

    // 첨부파일 다운로드
    @RequestMapping(value = "/jpa/board/file", method = RequestMethod.GET)
    public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception{
        BoardFileEntity file = jpaBoardService.selectBoardFileInformation(boardIdx, idx);
        if(!ObjectUtils.isEmpty(file)) {
            String fileName = file.getOriginalFileName();

            byte[] files = FileUtils.readFileToByteArray(new File(file.getStoredFilePath()));

            response.setContentType("application/octet-stream");
            response.setContentLength(files.length);
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            response.getOutputStream().write(files);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

}
