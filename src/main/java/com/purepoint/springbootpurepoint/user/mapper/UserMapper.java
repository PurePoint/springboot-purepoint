package com.purepoint.springbootpurepoint.user.mapper;

import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateRequestDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateSocialRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);

    User toUser(UserCreateRequestDto requestDto);

    User toUser(UserCreateSocialRequestDto requestDto);}
