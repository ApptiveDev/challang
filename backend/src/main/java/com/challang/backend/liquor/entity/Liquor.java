package com.challang.backend.liquor.entity;

import com.challang.backend.tag.entity.LiquorTag;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Table(name = "liquor")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Liquor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private LiquorLevel level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private LiquorType type;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "base", nullable = false)
    private String base;

    @Column(name = "origin", nullable = false)
    private String origin;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "min_abv", nullable = false)
    private Float minAbv;

    @Column(name = "max_abv",  nullable = false)
    private Float maxAbv;

    @OneToMany(mappedBy = "liquor")
    private final List<LiquorTag> liquorTags = new ArrayList<>();
}