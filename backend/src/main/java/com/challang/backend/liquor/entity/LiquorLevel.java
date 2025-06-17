package com.challang.backend.liquor.entity;

import com.challang.backend.liquor.dto.request.LiquorLevelRequest;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "liquor_level")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LiquorLevel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;  // ex. 저도수, 중도수, 고도수

    public LiquorLevel(String name) {
        this.name = name;
    }

    public void update(LiquorLevelRequest request) {
        this.name = request.name();
    }
}
