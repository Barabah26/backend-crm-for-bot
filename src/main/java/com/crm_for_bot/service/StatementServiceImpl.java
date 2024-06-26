package com.crm_for_bot.service;

import com.crm_for_bot.dao.StatementRepository;
import com.crm_for_bot.entity.Statement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatementServiceImpl implements StatementService {

    private StatementRepository statementRepository;

    @Override
    public List<Statement> getStatements() {
        return statementRepository.findAll();
    }
}
