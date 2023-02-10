package com.alexgim.sharing.domain.comment;

import com.alexgim.sharing.domain.BaseEntity;
import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.web.dto.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @DynamicInsert             // 엔티티 생성시 자동 값 입력
@Table(name = "comment")
@Entity
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 부모 정의 (셀프) -> 이해가 살짝 안됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "par_comment_id")
    private Comment superComment;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private CommentStatus status;

    // 자식 정의
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "superComment")
    private List<Comment> subCommentList = new ArrayList<>();
}
