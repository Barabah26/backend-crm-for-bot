package com.crm_for_bot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatementDto {
    private Long id;
    private String fullName;
    private String groupName;
    private String phoneNumber;
    private String typeOfStatement;
    private String institute;
    private String yearEntry;

}
