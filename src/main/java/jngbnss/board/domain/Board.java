package jngbnss.board.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // jpa가 관리해줌
@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA용 기본 생성자
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id@GeneratedValue
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 저장하기 전 실행됨
    @PrePersist
    public void onPrePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.modifiedDate = now;
    }

    // 업데이트 하기 전 실행됨
    @PreUpdate
    public void onPreUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

}
