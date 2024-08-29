package com.crm_for_bot.service;

import com.crm_for_bot.dao.StatementRepository;
import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.entity.StatementInfo;
import com.crm_for_bot.exception.RecourseNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private StatementDto mapToStatementDto(Object[] result) {
        StatementDto dto = new StatementDto();
        dto.setId((Long) result[0]);
        dto.setFullName((String) result[1]);
        dto.setGroupName((String) result[2]);
        dto.setPhoneNumber((String) result[3]);
        dto.setTypeOfStatement((String) result[4]);
        dto.setInstitute((String) result[5]);
        dto.setYearEntry((String) result[6]);
        return dto;
    }

    @Override
    public void updateStatementStatus(Long statementId) {
        StatementInfo statement = statementRepository.findById(statementId).orElseThrow(
                () -> new RecourseNotFoundException("Statement is not found with id: " + statementId)
        );
        statement.setStatus(true);
        statementRepository.save(statement);
    }
}

