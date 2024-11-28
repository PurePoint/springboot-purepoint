package com.purepoint.springbootpurepoint.youtube.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "video")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Video {

    @Id
    @Column(name = "video_id", nullable = false)
    private String videoId;

    @Column(name = "video_title", nullable = false)
    private String videoTitle;

    @Column(name = "video_description")
    private String videoDescription;

    @Column(name = "video_published_at")
    private String videoPublishedAt;

    @Column(name = "video_thumbnail")
    private String videoThumbnail;
}