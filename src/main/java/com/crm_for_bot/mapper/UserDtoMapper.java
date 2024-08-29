package com.crm_for_bot.mapper;


import com.crm_for_bot.dto.UserDto;
import com.crm_for_bot.entity.Role;
import com.crm_for_bot.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper extends DtoMapperFacade<User, UserDto> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDtoMapper() {
        super(User.class, UserDto.class);
    }

    @Override
    protected void decorateEntity(User user, UserDto dto) {
        user.setUserName(dto.getUsername());
        user.setEncryptedPassword(passwordEncoder.encode(dto.getPassword()));
        Set<Role> roles = user.getRoles();
        user.setRoles(roles);
    }

    public UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUserName());
        userDto.setPassword(user.getEncryptedPassword());
        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        userDto.setRoles(roles);
        return userDto;
    }

}
