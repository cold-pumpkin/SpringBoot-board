package board.board.common;

import board.board.dto.BoardFileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {
    public List<BoardFileDto> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }

        List<BoardFileDto> fileList = new ArrayList<>();

        // 오늘 날짜 형식으로 파일이 업로드될 폴더 생성
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();    // 오늘 날짜 확인
        String path = "/Users/philip/Documents/Studying/UploadTest/" + current.format(format);
        File file = new File(path);
        if(!file.exists()) {
            file.mkdir();
        }

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        String newFileName, originalFileExtension, contentType;

        while(iterator.hasNext()) {
            List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
            for(MultipartFile multipartFile : list) {
                contentType = multipartFile.getContentType();

                // 파일 형식 확인 후 이미지 확장자 지정
               if(ObjectUtils.isEmpty(contentType)) {
                    break;
                }
                else {
                    if(contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    }
                    else if(contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    }
                    else if(contentType.contains("image/png")) {
                        originalFileExtension = ".gif";
                    }
                    else {
                        break;
                    }
                }

                // 나노초 단위로 서버에 저장될 파일 이름 생성
                newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

                // 데이터베이스에 저장할 파일 정보를 BoardFileDto에 저장
                BoardFileDto boardFile = new BoardFileDto();
                boardFile.setBoardIdx(boardIdx);
                boardFile.setFileSize(multipartFile.getSize());
                boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
                boardFile.setStoredFilePath(path + "/" + newFileName);
                fileList.add(boardFile);

                // 업로드된 파일을 새로운 이름으로 바꿔 지정된 경로에 저장
                file = new File(path + "/" + newFileName);
                multipartFile.transferTo(file);

            }
        }
        return fileList;
    }
}
