package com.fantasyhelper.fantasyhelper.modle;
import javax.persistence.*;

@Entity
@Table(name = "playerlist")
public class PlayerList {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "playerName", unique = true, nullable = true)
    private String playerName;
    @Column(name="clubId", nullable = false)
    Integer clubid;
    @Column(name = "filedownloadLink", nullable = true)
    private String fileDownloadLink;
    @Column(name = "filePath", nullable = true)
    private String filePath;
    @Column(name="fileName", nullable = true)
    private String fileName;
    public int getId() {
        return id;
    }

    public String getFileDownloadLink() {
        return fileDownloadLink;
    }

    public void setFileDownloadLink(String fileDownloadLink) {
        this.fileDownloadLink = fileDownloadLink;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

}
