package board.board.service;

import board.board.common.FileUtils;
import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;
import board.board.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@Transactional
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insertBoard(board);
		/*
		if(!ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			String name;
			while(iterator.hasNext()) {
				name = iterator.next();
				log.debug("file tag name : " + name);
				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile multipartFile : list) {
					log.debug("start file information");
					log.debug("file name : " + multipartFile.getOriginalFilename());
					log.debug("file size : " + multipartFile.getSize());
					log.debug("file content type : " + multipartFile.getContentType());
					log.debug("end file information\n");
				}
			}
		}
		*/
		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		if(!CollectionUtils.isEmpty(list)) {
			boardMapper.insertBoardFileList(list);
		}
	}

	@Override
		public BoardDto selectBoardDetail(int boardIdx) throws Exception{
			BoardDto board = boardMapper.selectBoardDetail(boardIdx);
			boardMapper.updateHitCount(boardIdx);
		//int i = 10 / 0;	-- Transaction test
		return board;
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
}	

