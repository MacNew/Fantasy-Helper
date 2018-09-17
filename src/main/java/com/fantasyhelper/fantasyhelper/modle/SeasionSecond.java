package com.fantasyhelper.fantasyhelper.modle;

import javax.persistence.*;

@Entity
@Table(name = "seasonsecond")

public class SeasionSecond {
    @Id
    @Column(name = "season_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "clubId", unique = true)
    private  Integer clubId;

    @Column(name = "seasonName")
    private String seasonName;

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }
}
