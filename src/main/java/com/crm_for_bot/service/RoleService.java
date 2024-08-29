package com.crm_for_bot.service;

import com.crm_for_bot.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);

    Role save(Role role);
}
