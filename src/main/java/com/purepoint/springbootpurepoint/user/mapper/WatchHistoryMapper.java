package com.purepoint.springbootpurepoint.user.mapper;

import com.purepoint.springbootpurepoint.user.domain.WatchHistory;
import com.purepoint.springbootpurepoint.user.dto.request.WatchHistoryRequestDto;
import com.purepoint.springbootpurepoint.user.dto.response.WatchHistoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WatchHistoryMapper {
    WatchHistoryMapper INSTANCE = Mappers.getMapper(WatchHistoryMapper.class);

    WatchHistoryResponseDto toDto(WatchHistory dao);

    WatchHistory toEntity(WatchHistoryRequestDto dto);
}
