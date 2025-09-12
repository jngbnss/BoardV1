package jngbnss.board.dto;

import jngbnss.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;

    //정적 팩토리 메서드 추가
    public static BoardResponseDto FindFromBoard(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContent()
        );
    }

}
