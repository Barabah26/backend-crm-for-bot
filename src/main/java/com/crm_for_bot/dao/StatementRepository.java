package com.crm_for_bot.dao;

import com.crm_for_bot.entity.StatementInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<StatementInfo, Long> {

    @Query(value = "SELECT s.id, s.full_name, s.group_name, s.phone_number, s.type_of_statement, s.institute, s.year_entry, si.is_ready " +
            "FROM statements_info si " +
            "JOIN statements s ON si.id = s.id " +
            "WHERE si.status = false",
            nativeQuery = true)
    List<Object[]> findStatementsInfoWithStatusFalse();

}

