package com.challang.backend.feedback.entity;

import com.challang.backend.feedback.converter.FeedbackTypeConverter;
import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "liquor_feedback", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "liquor_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LiquorFeedback extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liquor_id", nullable = false)
    private Liquor liquor;

    @Convert(converter = FeedbackTypeConverter.class)
    private FeedbackType type;

    @Builder
    public LiquorFeedback(User user, Liquor liquor, FeedbackType type) {
        this.user = user;
        this.liquor = liquor;
        this.type = type;
    }

    public void updateType(FeedbackType type) {
        this.type = type;
    }
}
