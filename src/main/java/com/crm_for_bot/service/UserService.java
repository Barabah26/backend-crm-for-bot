package com.crm_for_bot.service;

import com.crm_for_bot.dto.UpdateUserDto;
import com.crm_for_bot.dto.UserDto;
import com.crm_for_bot.entity.User;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserService {
     Optional<User> getByLogin(@NonNull String login);
     UserDto registerUser(UserDto userDto);
     List<User> getAllUsers();
     void deleteUserByUsername(String username);
     UpdateUserDto updateUserPassword(String username, UpdateUserDto userDto);
}
