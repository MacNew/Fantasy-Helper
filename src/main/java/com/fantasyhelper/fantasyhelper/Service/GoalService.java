package com.fantasyhelper.fantasyhelper.Service;

import com.fantasyhelper.fantasyhelper.modle.ClubName;
import com.fantasyhelper.fantasyhelper.modle.GoalInformation;
import com.fantasyhelper.fantasyhelper.modle.GoalUpdate;
import com.fantasyhelper.fantasyhelper.repository.ClubsRepository;
import com.fantasyhelper.fantasyhelper.repository.GoalUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoalService {
    @Autowired
    GoalUpdateRepository goalRrepository;
    @Autowired
    ClubsRepository clubsRepository;
    public List<GoalInformation> findGoalInformationofAllClubs(Integer seasonId, Integer playerId) {
      List<GoalInformation> goalInformationslist = new ArrayList<>();
      List<GoalUpdate> goalUpdateList = goalRrepository.findBySeasonIdAndPlayerId(seasonId, playerId);
        for (GoalUpdate goalUpdate: goalUpdateList) {
            int cluId = goalUpdate.getClubId();
            ClubName clubName = clubsRepository.findById(cluId);
            GoalInformation goalInformation = new GoalInformation(clubName.getClubName(),
                    clubName.getFileName(),
                    goalUpdate.getHomeGoalScore(),
                    goalUpdate.getAwatyGoalScore(),
                    goalUpdate.getHomeGoalConsider(),goalUpdate.getAwayGoalConsider());
            goalInformationslist.add(goalInformation);
        }
        return goalInformationslist;
    }
}
