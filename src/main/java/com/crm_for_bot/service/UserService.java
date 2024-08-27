package com.crm_for_bot.service;

import com.crm_for_bot.dao.UserRepository;
import com.crm_for_bot.entity.User;
import lombok.NonNull;

import java.util.Optional;

public interface UserService {

     Optional<User> getByLogin(@NonNull String login);

}
