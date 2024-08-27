package com.crm_for_bot.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String username;


    @NotBlank(message = "Password cannot be blank")
    private String password;

}
