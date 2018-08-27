package com.fantasyhelper.fantasyhelper.modle;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="clubname")
public class ClubName {
  @Id
  @Column(name = "club_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "filedownloadLink")
  private String filedownloadLink;

    public String getFiledownloadLink() {
        return filedownloadLink;
    }

    public void setFiledownloadLink(String filedownloadLink) {
        this.filedownloadLink = filedownloadLink;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Column(name = "filepath")
  private String filepath;

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
