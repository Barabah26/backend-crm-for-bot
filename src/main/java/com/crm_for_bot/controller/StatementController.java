package com.crm_for_bot.controller;

import com.crm_for_bot.entity.Statement;
import com.crm_for_bot.service.StatementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/statements")
public class StatementController {

    private final StatementService statementService;

    @GetMapping
    public List<Statement> getAllStatements(){
        return statementService.getStatements();
    }
}
