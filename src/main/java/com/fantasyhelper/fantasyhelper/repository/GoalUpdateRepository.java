package com.fantasyhelper.fantasyhelper.repository;

import com.fantasyhelper.fantasyhelper.modle.GoalUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalUpdateRepository extends JpaRepository<GoalUpdate, Integer> {
   GoalUpdate findByClubIdAndSeasonIdAndPlayerId(Integer clubId, Integer seasonId, Integer playerId);
   List<GoalUpdate>findBySeasonIdAndPlayerId(Integer seasonId, Integer playerId);


}
