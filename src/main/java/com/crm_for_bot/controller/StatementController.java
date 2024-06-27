package com.crm_for_bot.controller;

import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.service.StatementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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
    public ResponseEntity<String> deleteStatement(@PathVariable("id") Long statementId){
        statementService.deleteStatement(statementId);
        return ResponseEntity.ok("Statement deleted successfully!");
    }
}
