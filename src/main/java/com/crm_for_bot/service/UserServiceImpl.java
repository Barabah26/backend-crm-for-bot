package com.crm_for_bot.service;

import com.crm_for_bot.dao.UserRepository;
import com.crm_for_bot.entity.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public Optional<User> getByLogin(@NonNull String login) {
        return userRepository.findUsersByUserName(login);
    }

}