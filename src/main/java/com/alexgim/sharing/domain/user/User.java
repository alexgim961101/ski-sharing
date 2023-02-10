package com.alexgim.sharing.domain.user;

import com.alexgim.sharing.domain.BaseEntity;
import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.domain.comment.Comment;
import com.alexgim.sharing.web.dto.UserStatusType;
import com.alexgim.sharing.web.dto.user.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    private String img;

    private String content;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private UserStatusType status;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    public UserDto toDto(){
        return UserDto.builder()
                .id(id)
                .nickname(nickname)
                .img(img)
                .phone(phone)
                .email(email)
                .status(status)
                .build();
    }
}
