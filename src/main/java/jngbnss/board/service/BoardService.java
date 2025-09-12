package jngbnss.board.service;

import jngbnss.board.domain.Board;
import jngbnss.board.dto.BoardDto;
import jngbnss.board.exception.BusinessLogicException;
import jngbnss.board.exception.ExceptionCode;
import jngbnss.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void deleteBoard(Long boardId){
        findBoardId(boardId);
        boardRepository.deleteById(boardId);
    }



    @Transactional
    public Long updateBoard(BoardDto boardDto,Long boardId){
        Board board = findBoardId(boardId);
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        return board.getId();

    }

    private Board findBoardId(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(()->new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    // 크리에트 보드
    public Long createBoard(BoardDto boardDto){
        Board board = new Board(boardDto.getTitle(), boardDto.getContent());
        return boardRepository.save(board).getId();
    }
}
