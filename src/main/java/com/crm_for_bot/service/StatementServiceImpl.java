package com.crm_for_bot.service;

import com.crm_for_bot.dao.StatementRepository;
import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.entity.Statement;
import com.crm_for_bot.exception.RecourseNotFoundException;
import com.crm_for_bot.mapper.StatementMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatementServiceImpl implements StatementService {

    private final StatementRepository statementRepository;

    @Override
    public List<StatementDto> getStatements() {
        List<Statement> statements = statementRepository.findByStatusFalse();
        if (statements.isEmpty()) {
            throw new RecourseNotFoundException("Statements are not found");
        }
        return statements.stream()
                .map(StatementMapper::mapToStatementDto)
                .collect(Collectors.toList());
    }
    @Override
    public void updateStatementStatus(Long statementId) {
        Statement statement = statementRepository.findById(statementId).orElseThrow(
                () -> new RecourseNotFoundException("Statement is not found with id: " + statementId)
        );
        statement.setStatus(true);  // Оновлення статусу на true
        statementRepository.save(statement);
    }
}
