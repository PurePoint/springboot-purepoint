package com.purepoint.springbootpurepoint.youtube.mapper;

import com.purepoint.springbootpurepoint.youtube.domain.Youtube;
import com.purepoint.springbootpurepoint.youtube.dto.YoutubeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface YoutubeMapper {
    YoutubeMapper INSTANCE = Mappers.getMapper(YoutubeMapper.class);

    List<YoutubeDto> toDto(List<Youtube> youtube);
}
