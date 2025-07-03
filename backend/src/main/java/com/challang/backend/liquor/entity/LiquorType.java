package com.challang.backend.liquor.entity;

import com.challang.backend.liquor.dto.request.LiquorTypeRequest;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "liquor_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LiquorType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;  // ex. 소주, 와인

    public LiquorType(String name) {
        this.name = name;
    }

    public void update(LiquorTypeRequest request) {
        this.name = request.name();
    }
}
