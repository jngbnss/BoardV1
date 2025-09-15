package jngbnss.board.service;

import jngbnss.board.domain.Board;
import jngbnss.board.dto.BoardDto;
import jngbnss.board.dto.BoardResponseDto;
import jngbnss.board.exception.BusinessLogicException;
import jngbnss.board.exception.ExceptionCode;
import jngbnss.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;



    public Page<BoardResponseDto> findAllBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(BoardResponseDto::FindFromBoard);
    }


        public BoardResponseDto findByBoardId(Long boardId){
        Board board = findBoardId(boardId);
        return BoardResponseDto.FindFromBoard(board);
    }

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
    public Board createBoard(BoardDto boardDto){
        Board board = new Board(boardDto.getTitle(), boardDto.getContent());
        return boardRepository.save(board);
    }
}
