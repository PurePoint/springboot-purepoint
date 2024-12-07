package com.purepoint.springbootpurepoint.playlist.domain;

import com.purepoint.springbootpurepoint.video.domain.Video;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "channel_id")
    private String channelId;

    @Column(name = "channel_title")
    private String channelTitle;

    @OneToMany(mappedBy = "playlist")
    private List<Video> videos;

}
