package com.purepoint.springbootpurepoint.community.domain;

import com.purepoint.springbootpurepoint.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id", nullable = false, unique = true)
    private UUID commentId;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "post_id", nullable = false)
    private UUID postId;

    @Column(name = "comment_contents", nullable = false)
    private String commentContents;

    @Column(name = "comment_update_at")
    private LocalDateTime commentUpdateAt;

    @Column(name = "comment_delete_at")
    private LocalDateTime commentDeleteAt;

    @Column(name = "depth", nullable = false)
    private Integer depth;

}
