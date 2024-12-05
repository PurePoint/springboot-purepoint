package com.purepoint.springbootpurepoint.playlist.mapper;

import com.purepoint.springbootpurepoint.playlist.domain.PlaylistLike;
import com.purepoint.springbootpurepoint.playlist.dto.PlaylistLikeStatusResDto;
import com.purepoint.springbootpurepoint.playlist.dto.UpdatePlaylistLikeStatusReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaylistLikeMapper {

    PlaylistLikeMapper INSTANCE = Mappers.getMapper(PlaylistLikeMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "playlistId", source = "playlistId"),
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "playlistLikeStatus", source = "playlistLikeStatus")
    })
    PlaylistLike toEntity(UpdatePlaylistLikeStatusReqDto updatePlaylistLikeStatusReqDto);

    @Mappings({
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "playlistId", source = "playlistId"),
            @Mapping(target = "playlistLikeStatus", source = "playlistLikeStatus")
    })
    PlaylistLikeStatusResDto toDto(PlaylistLike playlistLike);
}
