package com.challang.backend.tag.entity;

import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "liquor_tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LiquorTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liquor_id", nullable = false)
    private Liquor liquor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @Column(name = "is_core", nullable = false)
    private boolean isCore; // 핵심적 태그인지

    @Builder
    public LiquorTag(Liquor liquor, Tag tag, boolean isCore) {
        this.liquor = liquor;
        this.tag = tag;
        this.isCore = isCore;
    }
}
