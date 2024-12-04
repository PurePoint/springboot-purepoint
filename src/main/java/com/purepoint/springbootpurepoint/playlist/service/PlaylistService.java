package com.purepoint.springbootpurepoint.playlist.service;

import com.purepoint.springbootpurepoint.playlist.dto.PlaylistDto;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;

import java.util.List;

public interface PlaylistService {

    List<PlaylistDto> searchYoutubePlaylist(String query);

    List<VideoDto> getPlaylistVideos(String playlistId);
}
