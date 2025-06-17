package com.challang.backend.user.preference.entity;

import com.challang.backend.liquor.entity.LiquorLevel;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_liquor_preference_level")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LiquorPreferenceLevel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liquor_level_id", nullable = false)
    private LiquorLevel liquorLevel;

}