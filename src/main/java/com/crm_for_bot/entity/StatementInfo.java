package com.crm_for_bot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statements_info")
public class StatementInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "statement_id", nullable = false, unique = true)
    private Long statementId;

    @Column(name = "is_ready")
    private Boolean isReady;

    @Column(name = "status")
    private boolean status;
}

