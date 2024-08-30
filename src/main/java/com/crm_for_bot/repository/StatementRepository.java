package com.crm_for_bot.repository;

import com.crm_for_bot.entity.StatementInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<StatementInfo, Long> {

    @Query(value = "SELECT s.id, s.full_name, s.groupe, s.phone_number, s.type_of_statement, s.faculty, s.year_entry, si.is_ready " +
            "FROM statement_info si " +
            "JOIN statement s ON si.id = s.id " +
            "WHERE si.status = false",
            nativeQuery = true)
    List<Object[]> findStatementsInfoWithStatusFalse();

}

