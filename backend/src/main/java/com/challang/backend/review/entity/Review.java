package com.challang.backend.review.entity;

import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.review.dto.request.ReviewUpdateRequestDto;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private Liquor liquor;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Builder.Default
    @Column(name = "like_count")
    private int likeCount = 0;

    @Builder.Default
    @Column(name = "dislike_count")
    private int dislikeCount = 0;

    // 리뷰에 선택된 해시태그 목록
    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewTag> reviewTags = new ArrayList<>();

    public void update(ReviewUpdateRequestDto request, List<ReviewTag> newTags) {
        if (request.content() != null) {
            this.content = request.content();
        }
        if (request.imageUrl() != null) {
            this.imageUrl = request.imageUrl();
        }
        this.rating = request.rating();

        this.reviewTags.clear();
        this.reviewTags.addAll(newTags);
    }

    public void increaseReactionCount(ReactionType reactionType) {
        if (reactionType == ReactionType.LIKE) this.likeCount++;
        else this.dislikeCount++;
    }

    public void decreaseReactionCount(ReactionType reactionType) {
        if (reactionType == ReactionType.LIKE) this.likeCount--;
        else this.dislikeCount--;
    }

    public void changeReaction(ReactionType from, ReactionType to) {
        decreaseReactionCount(from);
        increaseReactionCount(to);
    }

    public void addTags(List<ReviewTag> reviewTags) {
        this.reviewTags.addAll(reviewTags);
    }
}