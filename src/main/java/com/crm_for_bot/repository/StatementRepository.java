package com.crm_for_bot.repository;

import com.crm_for_bot.entity.StatementInfo;
import com.crm_for_bot.util.StatementStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link StatementInfo} entities.
 */
@Repository
public interface StatementRepository extends JpaRepository<StatementInfo, Long> {

    /**
     * Retrieves statement information with a status of false.
     *
     * @return a list of statement information with a status of false
     */
    @Query(value = "SELECT s.id, s.full_name, s.group_name, s.phone_number, s.type_of_statement, s.faculty, s.year_entry, si.is_ready " +
            "FROM statement_info si " +
            "JOIN statement s ON si.id = s.id " +
            "WHERE si.status = false",
            nativeQuery = true)
    List<Object[]> findStatementsInfoWithStatusFalse();

    /**
     * Retrieves statement information with a status of false, filtered by faculty.
     *
     * @param faculty the faculty to filter by
     * @return a list of statement information with a status of false and the given faculty
     */
    @Query(value = "SELECT s.id, s.full_name, s.group_name, s.phone_number, s.type_of_statement, s.faculty, s.year_entry, si.is_ready " +
            "FROM statement_info si " +
            "JOIN statement s ON si.id = s.id " +
            "WHERE si.status = false AND s.faculty = :faculty",
            nativeQuery = true)
    List<Object[]> findStatementInfoByFaculty(@Param("faculty") String faculty);

    @Query(value = "SELECT s.id, s.full_name, s.group_name, s.phone_number, s.type_of_statement, s.faculty, s.year_entry, si.is_ready " +
            "FROM statement_info si " +
            "JOIN statement s ON si.id = s.id " +
            "WHERE si.status = false AND si.application_status = :status",
            nativeQuery = true)
    List<Object[]> findStatementInfoByStatus(@Param("status") String status);

}
