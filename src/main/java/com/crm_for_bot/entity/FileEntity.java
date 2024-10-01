package com.crm_for_bot.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "statement_id", unique = true)
    private StatementInfo statement;
}
