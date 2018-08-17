package com.fantasyhelper.fantasyhelper.modle;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="clubname")
public class Clubname {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "clubName", unique = true)
  private String clubName;

  @JsonProperty
  private boolean isCurrentSeasonPlaying;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public boolean isCurrentSeasonPlaying() {
        return isCurrentSeasonPlaying;
    }

    public void setCurrentSeasonPlaying(boolean currentSeasonPlaying) {
        isCurrentSeasonPlaying = currentSeasonPlaying;
    }
}
