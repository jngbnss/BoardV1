package jngbnss.board.controller;

import jngbnss.board.dto.BoardDto;
import jngbnss.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter@Setter
@RestController // json으로 만들어주는 기특한 녀석
@RequestMapping("/api/boards")//url기본값
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> postBoard(@RequestBody @Validated BoardDto boardDto){
        Long boardId = boardService.createBoard(boardDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(boardId);
    }


}
