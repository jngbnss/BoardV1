package jngbnss.board.dto;

import jngbnss.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private LocalDateTime CreatedDate;
    private LocalDateTime ModifiedDate;

    //정적 팩토리 메서드 추가
    public static BoardResponseDto FindFromBoard(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getCreatedDate(),
                board.getModifiedDate()
        );
    }

    public static BoardResponseDto from(Board board) {
        BoardResponseDto dto = new BoardResponseDto();
        dto.setBoardId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setCreatedDate(board.getCreatedDate());
        dto.setModifiedDate(board.getModifiedDate());
        return dto;
    }
}
