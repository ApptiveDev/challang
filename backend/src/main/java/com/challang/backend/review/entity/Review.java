package com.challang.backend.review.entity;

import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.liquor.entity.LiquorType;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liquor_id", nullable = false)
    private LiquorType liquor;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_url") // nullable = false 제거
    @Builder.Default // 빌더 사용 시 기본값 설정
    private String imageUrl = ""; // 기본값을 빈 문자열로 설정

    public void updateContent(String newContent) {
        this.content = newContent;
    }
}