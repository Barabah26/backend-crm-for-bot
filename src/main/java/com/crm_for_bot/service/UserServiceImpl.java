package com.crm_for_bot.service;

import com.crm_for_bot.dao.UserRepository;
import com.crm_for_bot.dto.UserDto;
import com.crm_for_bot.entity.User;
import com.crm_for_bot.exception.RecourseNotFoundException;
import com.crm_for_bot.mapper.UserDtoMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> getByLogin(@NonNull String login) {
        return userRepository.findUsersByUserName(login);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = userDtoMapper.convertToEntity(userDto);
        user.setUserName(userDto.getUsername());
        user.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(user);

        return userDtoMapper.convertToDto(savedUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        if (userRepository.findById(id).isEmpty()){
            throw new RecourseNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }

}