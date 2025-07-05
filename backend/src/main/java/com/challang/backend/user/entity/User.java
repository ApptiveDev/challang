package com.challang.backend.user.entity;

import com.challang.backend.archive.entity.Archive;
import com.challang.backend.preference.entity.LiquorPreferenceLevel;
import com.challang.backend.preference.entity.LiquorPreferenceType;
import com.challang.backend.review.entity.Review;
import com.challang.backend.review.entity.ReviewReaction;
import com.challang.backend.user.converter.UserRoleConverter;
import com.challang.backend.util.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;
import lombok.AccessLevel;
import com.challang.backend.preference.entity.LiquorPreferenceTag;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "oauth_id", unique = true)
    private String oauthId;

    @Column(name = "birth_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Column(name = "nickname", unique = true)
    private String nickname;

    // 0: 남자, 1: 여자
    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Convert(converter = UserRoleConverter.class)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type", nullable = false)
    private AuthType authType;

    //탈퇴 시 회원 정보를 지우기 위한 메서드들
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LiquorPreferenceTag> liquorPreferenceTags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LiquorPreferenceType> liquorPreferenceTypes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewReaction> reviewReactions = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LiquorPreferenceLevel> liquorPreferenceLevels = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Archive> archives = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();


    @Builder(access = AccessLevel.PRIVATE)
    private User(String email, String password, String oauthId, LocalDate birthDate, Integer gender,
                 UserRole role, AuthType authType) {
        this.email = email;
        this.password = password;
        this.oauthId = oauthId;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
        this.authType = authType;
    }

    // 이메일 회원가입 시 사용
    public static User createWithEmail(String email, String password, LocalDate birthDate, Integer gender,
                                       UserRole role) {
        return User.builder()
                .email(email)
                .password(password)
                .birthDate(birthDate)
                .gender(gender)
                .role(role)
                .authType(AuthType.EMAIL)
                .build();
    }

    // 카카오 회원가입 시 사용
    public static User createWithOauth(String oauthId, LocalDate birthDate, Integer gender, UserRole role) {
        return User.builder()
                .oauthId(oauthId)
                .birthDate(birthDate)
                .gender(gender)
                .role(role)
                .authType(AuthType.KAKAO)
                .build();
    }


}
