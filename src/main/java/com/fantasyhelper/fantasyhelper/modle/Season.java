package com.fantasyhelper.fantasyhelper.modle;

import javax.persistence.*;

@Entity
@Table(name = "seasion")
public class Season {
    @Id
    @Column(name = "seasionId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "seasonName", unique = true, nullable = false)
    private String seasonName;

    @Column(name = "winerClubName", nullable = false)
    private String winerClubName;

    @Column(name = "runnerUpClubName", nullable = false)
    private String runnerUpClubName;

    @Column(name = "topScorer", nullable = false)
    private String topScorer;

    @Column(name = "topGoalKepper", nullable = false)
    private String topGoalKepper;

    @Column(name = "topDefender", nullable = false)
    private String topDefender;

    @Column(name = "topMidFielder", nullable = false)
    private String topMidFielder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getWinerClubName() {
        return winerClubName;
    }

    public void setWinerClubName(String winerClubName) {
        this.winerClubName = winerClubName;
    }

    public String getRunnerUpClubName() {
        return runnerUpClubName;
    }

    public void setRunnerUpClubName(String runnerUpClubName) {
        this.runnerUpClubName = runnerUpClubName;
    }

    public String getTopScorer() {
        return topScorer;
    }

    public void setTopScorer(String topScorer) {
        this.topScorer = topScorer;
    }

    public String getTopGoalKepper() {
        return topGoalKepper;
    }

    public void setTopGoalKepper(String topGoalKepper) {
        this.topGoalKepper = topGoalKepper;
    }

    public String getTopDefender() {
        return topDefender;
    }

    public void setTopDefender(String topDefender) {
        this.topDefender = topDefender;
    }

    public String getTopMidFielder() {
        return topMidFielder;
    }

    public void setTopMidFielder(String topMidFielder) {
        this.topMidFielder = topMidFielder;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
}
