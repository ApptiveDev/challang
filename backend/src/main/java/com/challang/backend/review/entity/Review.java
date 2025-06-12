package com.challang.backend.review.entity;

import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", nullable = false)
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liquor_id", nullable = false)
    private Liquor liquor;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

}
