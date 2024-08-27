package com.crm_for_bot.service;

import com.crm_for_bot.dto.StatementDto;

import java.util.List;

public interface StatementService {
    List<StatementDto> getStatementsInfoWithStatusFalse();

    void updateStatementStatus(Long statementId);
}
