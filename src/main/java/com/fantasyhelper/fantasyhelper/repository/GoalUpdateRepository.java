package com.fantasyhelper.fantasyhelper.repository;

import com.fantasyhelper.fantasyhelper.modle.GoalUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalUpdateRepository extends JpaRepository<GoalUpdate, Integer> {
   GoalUpdate findByClubIdAndSeasonIdAndPlayerId(Integer clubId, Integer seasonId, Integer playerId);


}
