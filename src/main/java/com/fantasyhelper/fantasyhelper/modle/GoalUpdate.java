package com.fantasyhelper.fantasyhelper.modle;

import javax.persistence.*;

@Entity
@Table(name="goalUpdate")
public class GoalUpdate {
    @Id
    @Column(name ="goal_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "player_id")
    private Integer playerId;
    @Column(name = "season_id")
    private Integer seasonId;
    @Column(name = "home_goal_score")
    private Integer homeGoalScore;

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    @Column(name = "away_goal_score")
    private Integer awatyGoalScore;
    @Column(name = "home_goal_consider")
    private Integer homeGoalConsider;
    @Column(name = "away_goal_consider")
    private Integer awayGoalConsider;

    @Column(name = "club_id")
    private Integer clubId;

    public Integer getHomeGoalConsider() {
        return homeGoalConsider;
    }

    public void setHomeGoalConsider(Integer homeGoalConsider) {
        this.homeGoalConsider = homeGoalConsider;
    }

    public Integer getAwayGoalConsider() {
        return awayGoalConsider;
    }

    public void setAwayGoalConsider(Integer awayGoalConsider) {
        this.awayGoalConsider = awayGoalConsider;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Integer seasonId) {
        this.seasonId = seasonId;
    }

    public Integer getHomeGoalScore() {
        return homeGoalScore;
    }

    public void setHomeGoalScore(Integer homeGoalScore) {
        this.homeGoalScore = homeGoalScore;
    }

    public Integer getAwatyGoalScore() {
        return awatyGoalScore;
    }

    public void setAwatyGoalScore(Integer awatyGoalScore) {
        this.awatyGoalScore = awatyGoalScore;
    }
}
