package jngbnss.board.dto;

import jakarta.validation.constraints.NotEmpty;
import jngbnss.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BoardDto {
    @NotEmpty(message = "제목을 입력해주세요")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요")
    private String content;


}
