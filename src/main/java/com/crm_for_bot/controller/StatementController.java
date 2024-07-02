package com.crm_for_bot.controller;

import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.service.StatementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/statements")
public class StatementController {

    private final StatementService statementService;

    @GetMapping
    public ResponseEntity<List<StatementDto>> getAllStatements() {
        List<StatementDto> statements = statementService.getStatements();
        return ResponseEntity.ok(statements);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> updateStatementStatus(@PathVariable("id") Long statementId){
        statementService.updateStatementStatus(statementId);
        return ResponseEntity.ok("Statement status updated successfully!");
    }
}

