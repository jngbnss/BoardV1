package jngbnss.board.controller;

import jngbnss.board.domain.Board;
import jngbnss.board.dto.BoardDto;
import jngbnss.board.dto.BoardResponseDto;
import jngbnss.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Getter@Setter
@RestController // json으로 만들어주는 기특한 녀석
@RequestMapping("/api/boards")//url기본값
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;


    @GetMapping
    public ResponseEntity<Page<BoardResponseDto>> getAllBoards(
            @RequestParam(value = "page",defaultValue = "1")int page,
            @RequestParam(value = "size",defaultValue = "5")int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<BoardResponseDto> boards = boardService.findAllBoards(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(boards);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto>getBoard(@PathVariable("boardId")Long boardId){
        BoardResponseDto boardResponseDto = boardService.findByBoardId(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Long>  deleteBoard(@PathVariable("boardId")Long boardId){
        boardService.deleteBoard(boardId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<Long> patchBoard(@PathVariable("boardId")Long boardId,
                                           @RequestBody @Validated BoardDto boardDto){
        boardService.updateBoard(boardDto,boardId);
        return ResponseEntity.status(HttpStatus.OK).body(boardId);
    }

    // 근데 모델로 받아서 그런거아냐? 모데롤 안받고싶은데

    @PostMapping
    public ResponseEntity<BoardResponseDto> postBoard(@RequestBody @Validated BoardDto boardDto){
        Board savedBoard = boardService.createBoard(boardDto);
        BoardResponseDto boardResponseDto = BoardResponseDto.from(savedBoard);
        return ResponseEntity.status(HttpStatus.CREATED).body(boardResponseDto);
    }


}
