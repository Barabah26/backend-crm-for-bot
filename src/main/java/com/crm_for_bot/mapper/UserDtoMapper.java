package com.crm_for_bot.mapper;


import com.crm_for_bot.dto.UserDto;
import com.crm_for_bot.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    }

}
