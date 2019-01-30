package com.fantasyhelper.fantasyhelper.modle;

public class GoalInformation {
     String  clubFileName;
     String clubName;
     Integer homeGoalScore;

    public String getClubFileName() {
        return clubFileName;
    }

    Integer awayGoalScore;

    public GoalInformation(String clubName, String clubFileName, Integer homeGoalScore, Integer awayGoalScore, Integer homeGoalConsider, Integer awayGoalConsider) {
        this.clubName = clubName;
        this.homeGoalScore = homeGoalScore;
        this.awayGoalScore = awayGoalScore;
        this.homeGoalConsider = homeGoalConsider;
        this.awayGoalConsider = awayGoalConsider;
        this.clubFileName = clubFileName;
    }

    public String getClubName() {
        return clubName;
    }

    public Integer getHomeGoalScore() {
        return homeGoalScore;
    }

    public Integer getAwayGoalScore() {
        return awayGoalScore;
    }

    public Integer getHomeGoalConsider() {
        return homeGoalConsider;
    }

    public Integer getAwayGoalConsider() {
        return awayGoalConsider;
    }

    Integer homeGoalConsider;
     Integer awayGoalConsider;


}
