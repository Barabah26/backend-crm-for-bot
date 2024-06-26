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
@Table(name = "statements")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "statement")
    private String statement;

    @Column(name = "status")
    private boolean status;

    @Column(name = "telegram_id")
    private Long telegramId;

    @Column(name = "year_entry")
    private String yearEntry;

    public Statement(Long id, String fullName, String groupName, String phoneNumber, String statement, String yearEntry) {
        this.id = id;
        this.fullName = fullName;
        this.groupName = groupName;
        this.phoneNumber = phoneNumber;
        this.statement = statement;
        this.yearEntry = yearEntry;
    }
}
