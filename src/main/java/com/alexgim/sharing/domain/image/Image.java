package com.alexgim.sharing.domain.image;

import com.alexgim.sharing.domain.BaseEntity;
import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.web.dto.image.ImageDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", nullable = false)
    private Long id;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String name;  // 사진 원본 이름

    @Column(nullable = false)
    private String url;  // 사진이 저장된 url

    public ImageDto toDto() {
        // board는 굳이 넣을 필요가 없고 넣어도 무한 참조 오류만 발생
        return ImageDto.builder()
                .id(id)
                .name(name)
                .url(url)
                .build();
    }
}
