package com.crm_for_bot.repository;

import com.crm_for_bot.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    @Query(value = "INSERT INTO files (data, file_name, file_type, statement_id) VALUES (CAST(?1 AS BYTEA), ?2, ?3, ?4) RETURNING id", nativeQuery = true)
    Long saveFile(byte[] data, String fileName, String fileType, Long statementId);

}
