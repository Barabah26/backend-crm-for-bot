package com.crm_for_bot.service;

import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.util.StatementStatus;

import java.util.List;

/**
 * Service interface for managing statements.
 */
public interface StatementService {

    /**
     * Retrieves statements with a status of false.
     *
     * @return a list of StatementDto objects
     */
    List<StatementDto> getStatementsInfoWithStatusFalse();

    /**
     * Retrieves statements based on the faculty.
     *
     * @param faculty the faculty name to filter by
     * @return a list of StatementDto objects
     */
    List<StatementDto> getStatementsInfoByFaculty(String faculty);

    /**
     * Updates the status of a statement to true.
     *
     * @param statementId the ID of the statement to update
     */
    void updateStatementStatus(Long statementId, StatementStatus status);
}
