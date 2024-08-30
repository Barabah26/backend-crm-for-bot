package com.crm_for_bot.service;

import com.crm_for_bot.entity.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Optional<Role> findByName(String roleName);

    Role save(Role role);
}
