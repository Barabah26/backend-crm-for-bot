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
@Table(name = "statement_info")
public class StatementInfo {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "is_ready")
    private Boolean isReady;

    @Column(name = "status")
    private boolean status;
}

