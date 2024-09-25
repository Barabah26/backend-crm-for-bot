package com.crm_for_bot.service;

import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.entity.StatementInfo;
import com.crm_for_bot.exception.RecourseNotFoundException;
import com.crm_for_bot.repository.StatementRepository;
import com.crm_for_bot.util.StatementStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the StatementService interface.
 */
@Service
@AllArgsConstructor
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;

    @Override
    public List<StatementDto> getStatementsInfoWithStatusFalse() {
        List<Object[]> results = statementRepository.findStatementsInfoWithStatusFalse();
        if (results.isEmpty()) {
            throw new RecourseNotFoundException("Statements are not found");
        }

        return results.stream().map(this::mapToStatementDto).collect(Collectors.toList());
    }

    @Override
    public List<StatementDto> getStatementsInfoByFaculty(String faculty) {
        List<Object[]> results = statementRepository.findStatementInfoByFaculty(faculty);
        if (results.isEmpty()) {
            throw new RecourseNotFoundException("Statements are not found");
        }

        return results.stream().map(this::mapToStatementDto).collect(Collectors.toList());
    }

    private StatementDto mapToStatementDto(Object[] result) {
        StatementDto dto = new StatementDto();
        dto.setId((Long) result[0]);
        dto.setFullName((String) result[1]);
        dto.setGroupName((String) result[2]);
        dto.setPhoneNumber((String) result[3]);
        dto.setTypeOfStatement((String) result[4]);
        dto.setFaculty((String) result[5]);
        dto.setYearEntry((String) result[6]);
        return dto;
    }

    @Override
    public void updateStatementStatus(Long statementId, StatementStatus status) {
        StatementInfo statement = statementRepository.findById(statementId).orElseThrow(
                () -> new RecourseNotFoundException("Statement is not found with id: " + statementId)
        );
        statement.setApplicationStatus(StatementStatus.valueOf(status.name()));
        if (status == StatementStatus.READY) {
            statement.setStatus(true);
        }

        statementRepository.save(statement);
    }

    @Override
    public List<StatementDto> getStatementsInfoByStatus(StatementStatus status) {
        List<Object[]> results = statementRepository.findStatementInfoByStatus(status.name());
        if (results.isEmpty()) {
            throw new RecourseNotFoundException("Statements are not found");
        }

        return results.stream().map(this::mapToStatementDto).collect(Collectors.toList());
    }
}
