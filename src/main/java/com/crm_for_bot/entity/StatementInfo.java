package com.crm_for_bot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing information related to statements in the application.
 * This class holds details about the readiness and status of statements.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statement_info")
public class StatementInfo {

    /**
     * The unique identifier for the statement info.
     * This field is used as the primary key and must be unique.
     */
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    /**
     * Indicates whether the statement is ready.
     * This field is used to track the readiness status of the statement.
     */
    @Column(name = "is_ready")
    private Boolean isReady;

    /**
     * The status of the statement.
     * This field is a boolean that represents the current status of the statement.
     */
    @Column(name = "status")
    private boolean status;

    /**
     * The current application status of the statement.
     * This field represents the process status (e.g., "IN_PROGRESS", "COMPLETED", "PENDING").
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    private String applicationStatus;

}
