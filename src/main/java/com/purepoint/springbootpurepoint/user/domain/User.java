package com.purepoint.springbootpurepoint.user.domain;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "provider_id")  // 프로바이더 마다의 고유 식별자 (구글:sub, 카카오:id ..)
    private String providerId;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    // 기본값을 NULL로 설정
    @Column(name = "deleted_at", columnDefinition = "DATETIME DEFAULT NULL")
    private LocalDateTime deletedAt;

    // 나중에 암호화 해야함.
    @Column(name = "password")
    private String password;
}
