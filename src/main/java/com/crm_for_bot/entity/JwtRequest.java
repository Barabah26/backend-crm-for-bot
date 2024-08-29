package com.crm_for_bot.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    private String username;

    private String password;
}
