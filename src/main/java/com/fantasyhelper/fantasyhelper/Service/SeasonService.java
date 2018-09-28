package com.fantasyhelper.fantasyhelper.Service;

import com.fantasyhelper.fantasyhelper.modle.Season;
import com.fantasyhelper.fantasyhelper.repository.ClubsRepository;
import com.fantasyhelper.fantasyhelper.repository.PlayerRepository;
import com.fantasyhelper.fantasyhelper.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonService {
    @Autowired
    SeasonRepository seasonRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    ClubsRepository clubsRepository;
    public Season saveSeason(Season season) {
        Season season1 = seasonRepository.save(season);
        if (season1 != null) {
            return season;
        } else {
            return null;
        }
    }

    public Object getAll() {
        List<Season> seasonslist = seasonRepository.findAll();
        List<Season> returnSeasionList = new ArrayList<>();
        System.out.println();
        for (Season season:seasonslist) {
            Season returnSeason = new Season();
            returnSeason.setId(season.getId());
            returnSeason.setSeasonName(season.getSeasonName());
            returnSeason.setRunnerUpClubName(clubsRepository.findById(Integer.parseInt(season.getRunnerUpClubName())).getClubName());
            returnSeason.setWinerClubName(clubsRepository.findById(Integer.parseInt(season.getWinerClubName())).getClubName());
            returnSeason.setWinerClubName(clubsRepository.findById(Integer.parseInt(season.getWinerClubName())).getClubName());
            returnSeason.setTopScorer(playerRepository.findById(Integer.parseInt(season.getTopScorer())).get().getPlayerName());
            returnSeason.setTopGoalKepper(playerRepository.findById(Integer.parseInt(season.getTopGoalKepper())).get().getPlayerName());
            returnSeason.setTopDefender(playerRepository.findById(Integer.parseInt(season.getTopDefender())).get().getPlayerName());
            returnSeason.setTopMidFielder(playerRepository.findById(Integer.parseInt(season.getTopMidFielder())).get().getPlayerName());
            returnSeasionList.add(returnSeason);
        }
        return returnSeasionList;
    }
}
