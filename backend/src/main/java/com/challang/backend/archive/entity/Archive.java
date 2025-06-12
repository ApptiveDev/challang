package com.challang.backend.archive.entity;

import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.user.entity.User;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "archive", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "liquor_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Archive extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liquor_id", nullable = false)
    private Liquor liquor;

}
