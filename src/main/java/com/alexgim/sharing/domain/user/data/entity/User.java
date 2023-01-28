package com.alexgim.sharing.domain.user.data.entity;

import com.alexgim.sharing.domain.board.data.entity.Board;
import com.alexgim.sharing.common.BaseEntity;
import com.alexgim.sharing.domain.user.data.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")                            // DB 컬럼에는 스네이크 케이스로 저장해야 하기 때문에 따로 이름 지정
    private Long userId;

    @Column(nullable = false)
    private String nickname;  // 유저의 아이디

    @Column(nullable = false)
    private String password;  // 유저의 비밀번호

    private String img;  // 유저 프로필 사진

    @Column(nullable = false)
    private String addr;  // 유저 주소

    private String content;  // 유저 자기소개

    private String email;  // 유저의 이메일

    @Column(nullable = false)
    private RoleType status;  // 유저의 상태 (admin, normal, stop, delete)


}
