package com.challang.backend.tag.entity;

import com.challang.backend.tag.dto.request.TagRequest;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;

@Entity
@Table(name = "tag")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;  // ex. 드라이, 과일향, 오크향

    public Tag(String name) {
        this.name = name;
    }

    public void update(TagRequest request) {
        this.name = request.name();
    }
}
