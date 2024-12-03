package com.purepoint.springbootpurepoint.playlist.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "playlist")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Playlist {

    @Id
    @Column(name = "playlist_id", nullable = false)
    private String playlistId;

    @Column(name = "playlist_kind", nullable = false)
    private String playlistKind;

    @Column(name = "playlist_title", nullable = false)
    private String playlistTitle;

    @Column(name = "playlist_description")
    private String playlistDescription;

    @Column(name = "playlist_published_at")
    private LocalDateTime playlistPublishedAt;

    @Column(name = "playlist_thumbnail")
    private String playlistThumbnail;

    @Column(name = "playlist_category")
    private Integer playlistCategory;

//    @OneToMany(mappedBy = "playlist")
//    private List<Video> videos;  // 여러 개의 Video가 하나의 Playlist에 포함될 수 있음

}
