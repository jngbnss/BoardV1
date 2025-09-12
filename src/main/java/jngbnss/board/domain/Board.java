package jngbnss.board.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // jpa가 관리해줌
@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA용 기본 생성자
public class Board {
    @Id@GeneratedValue
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
