package com.challang.backend.liquor.entity;

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

}
