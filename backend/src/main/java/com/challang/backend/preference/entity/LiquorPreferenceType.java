package com.challang.backend.preference.entity;

import com.challang.backend.liquor.entity.LiquorType;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_liquor_preference_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LiquorPreferenceType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liquor_type_id", nullable = false)
    private LiquorType liquorType;

    @Builder
    public LiquorPreferenceType(User user, LiquorType liquorType) {
        this.user = user;
        this.liquorType = liquorType;
    }
}
