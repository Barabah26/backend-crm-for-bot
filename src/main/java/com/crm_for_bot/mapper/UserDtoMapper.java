package com.crm_for_bot.mapper;

import com.crm_for_bot.dto.UserDto;
import com.crm_for_bot.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDtoMapper extends Mappable<User, UserDto> {
    @Override
    User toEntity(UserDto dto);

    @Override
    UserDto toDto(User entity);

    @Override
    List<UserDto> toDto(List<User> entity);
}
