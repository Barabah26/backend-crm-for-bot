package com.crm_for_bot.mapper;

import com.crm_for_bot.dto.UserDto;
import com.crm_for_bot.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper extends DtoMapperFacade<User, UserDto> {

    public UserDtoMapper() {
        super(User.class, UserDto.class);
    }

    @Override
    protected void decorateDto(UserDto dto, User user) {
        dto.setUserName(user.getUserName());
        dto.setSysRoles(String.valueOf(user.getRoles()));
    }
}
