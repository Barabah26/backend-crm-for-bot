package com.crm_for_bot.service;

import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.entity.Statement;

import java.util.List;

public interface StatementService {
    List<StatementDto> getStatements();

    void deleteStatement(Long statementId);
}
