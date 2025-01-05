package com.budgetupdate.budgetupdate.Repositories;

import com.budgetupdate.budgetupdate.Models.Challenge;
import com.budgetupdate.budgetupdate.Models.ChallengeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByUsers_Id(Long userId);

    Optional<Challenge> findTopByIsGlobalAndStatusOrderByStartDateDesc(boolean isGlobal, ChallengeStatus status);

    @Query("SELECT c FROM Challenge c WHERE c.id NOT IN " +
            "(SELECT ch.id FROM User u JOIN u.challenges ch WHERE u.id = :userId)")
    List<Challenge> findAvailableChallengesForUser(@Param("userId") Long userId);

}
