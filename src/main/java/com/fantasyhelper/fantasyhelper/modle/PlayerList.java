package com.fantasyhelper.fantasyhelper.modle;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playerlist")
public class PlayerList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    ///////////////////////////////////////////////
    @Column(name = "playerName", unique = true, nullable = true)
    private String playerName;
    //////////////////////////////////////////////

    @Column(name="club_id", nullable = false)
    Integer clubid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getClubid() {
        return clubid;
    }

    public void setClubid(Integer clubid) {
        this.clubid = clubid;
    }

    public List<ClubName> getClubNameList() {
        return clubNameList;
    }

    public void setClubNameList(List<ClubName> clubNameList) {
        this.clubNameList = clubNameList;
    }

    ///////////////////////////////////////
    @OneToMany
    @JoinColumn(name = "club_id")
    List<ClubName> clubNameList = new ArrayList<ClubName>();

}
