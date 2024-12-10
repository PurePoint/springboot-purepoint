package com.purepoint.springbootpurepoint.playlist.service;

import com.purepoint.springbootpurepoint.playlist.domain.PlaylistLike;
import com.purepoint.springbootpurepoint.playlist.dto.*;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;

import java.util.List;

public interface PlaylistService {

    PlaylistLikesResDto getPlaylistLikes(String playlistId);

    List<PlaylistDto> searchYoutubePlaylist(String query);

    List<VideoDto> getPlaylistVideos(String playlistId);

    PlaylistIdResDto getPlaylistId(String videoId);

    PlaylistLikeStatusResDto getPlaylistLikeStatus(PlaylistLikeStatusReqDto playlistLikeStatusReqDto);

    PlaylistLike updatePlaylistLike(UpdatePlaylistLikeStatusReqDto updatePlaylistLikeStatusReqDto);

}
