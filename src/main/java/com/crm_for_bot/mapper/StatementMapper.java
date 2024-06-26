package com.crm_for_bot.mapper;

import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.entity.Statement;

public class StatementMapper {
    public static StatementDto mapToStatementDto(Statement statement) {
        return new StatementDto(statement.getId(),
                statement.getFullName(),
                statement.getGroupName(),
                statement.getPhoneNumber(),
                statement.getStatement(),
                statement.getYearEntry());
    }

    public static Statement mapToStatement(Statement statement){
        return new Statement(
                statement.getId(),
                statement.getFullName(),
                statement.getGroupName(),
                statement.getPhoneNumber(),
                statement.getStatement(),
                statement.getYearEntry()
        );
    }
}
