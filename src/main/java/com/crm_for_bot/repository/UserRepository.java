package com.crm_for_bot.repository;

import com.crm_for_bot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByUserName(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.encryptedPassword = :newPassword WHERE u.userName = :username")
    void updateUserByUsername(@Param("username") String username,
                        @Param("newPassword") String newPassword);

}
