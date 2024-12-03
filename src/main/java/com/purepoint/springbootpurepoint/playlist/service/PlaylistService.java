package com.purepoint.springbootpurepoint.playlist.service;

import com.purepoint.springbootpurepoint.playlist.dto.PlaylistDto;

import java.util.List;

public interface PlaylistService {

    List<PlaylistDto> searchYoutubePlaylist(String query);
}
