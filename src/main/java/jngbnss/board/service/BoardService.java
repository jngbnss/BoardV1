package jngbnss.board.service;

import jngbnss.board.domain.Board;
import jngbnss.board.dto.BoardDto;
import jngbnss.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 크리에트 보드
    public Long createBoard(BoardDto boardDto){
        Board board = new Board(boardDto.getTitle(), boardDto.getContent());
        return boardRepository.save(board).getId();
    }
}
