package com.purepoint.springbootpurepoint.community.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Community {

    @Id
    @Column(name = "board_id", columnDefinition = "CHAR(36)")
    private UUID boardId = UUID.randomUUID();

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Column(name = "post_contents")
    private String postContent;

    @Column(name = "post_views")
    private Integer postView;

    @Column(name = "post_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime postAt;

    @Column(name = "post_update_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime postUpdateAt;

    @Column(name = "post_delete_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime postDeleteAt;

}
