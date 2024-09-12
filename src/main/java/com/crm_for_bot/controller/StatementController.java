package com.crm_for_bot.controller;

import com.crm_for_bot.dto.StatementDto;
import com.crm_for_bot.exception.RecourseNotFoundException;
import com.crm_for_bot.service.StatementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling endpoints related to statements.
 * Provides methods for retrieving all statements, retrieving statements by faculty, and updating statement status.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/statements")
@Slf4j
public class StatementController {

    /**
     * Service for handling statement operations.
     */
    private final StatementService statementService;

    /**
     * Retrieves all statements with a status of false.
     *
     * @return ResponseEntity<List<StatementDto>> - the response containing a list of statements with status false, or no content if none found.
     */
    @GetMapping
    public ResponseEntity<List<StatementDto>> getAllStatements() {
        log.info("Fetching all statements with status false");
        List<StatementDto> statements = statementService.getStatementsInfoWithStatusFalse();
        if (statements.isEmpty()) {
            log.warn("No statements found with status false");
            return ResponseEntity.noContent().build();
        }
        log.info("Retrieved {} statements", statements.size());
        return ResponseEntity.ok(statements);
    }

    /**
     * Retrieves statements for a specific faculty.
     *
     * @param faculty the faculty name to filter the statements.
     * @return ResponseEntity<List<StatementDto>> - the response containing a list of statements for the given faculty, or no content if none found.
     */
    @GetMapping("{faculty}")
    public ResponseEntity<List<StatementDto>> getStatementsByFaculty(@PathVariable("faculty") String faculty) {
        log.info("Fetching statements for faculty: {}", faculty);
        List<StatementDto> statements;
        try {
            statements = statementService.getStatementsInfoByFaculty(faculty);
        } catch (IllegalArgumentException e) {
            log.error("Invalid faculty provided: {}", faculty, e);
            return ResponseEntity.badRequest().body(null);
        }

        if (statements.isEmpty()) {
            log.warn("No statements found for faculty: {}", faculty);
            return ResponseEntity.noContent().build();
        }

        log.info("Retrieved {} statements for faculty: {}", statements.size(), faculty);
        return ResponseEntity.ok(statements);
    }

    /**
     * Updates the status of a statement based on its ID.
     *
     * @param statementId the ID of the statement to update.
     * @return ResponseEntity<String> - the response indicating the result of the update operation.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> updateStatementStatus(@PathVariable("id") Long statementId) {
        log.info("Updating statement status for statement ID: {}", statementId);
        try {
            statementService.updateStatementStatus(statementId);
        } catch (RecourseNotFoundException e) {
            log.error("Statement with ID {} not found", statementId, e);
            return ResponseEntity.status(404).body("Statement not found!");
        } catch (Exception e) {
            log.error("Failed to update statement status for ID: {}", statementId, e);
            return ResponseEntity.status(500).body("Failed to update statement status!");
        }
        log.info("Statement status updated successfully for ID: {}", statementId);
        return ResponseEntity.ok("Statement status updated successfully!");
    }
}
