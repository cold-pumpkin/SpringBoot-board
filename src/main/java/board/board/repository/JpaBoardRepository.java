package board.board.repository;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer> {
    // CrudRepository 인터페이스가 제공하는 메서드
    List<BoardEntity> findAllByOrderByBoardIdxDesc();

    // 쿼리 메서드
    @Query("SELECT file FROM BoardFileEntity file WHERE board_idx = :boardIdx AND idx = :idx")
    BoardFileEntity findBoardFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);
}
