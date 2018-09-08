package com.fantasyhelper.fantasyhelper.modle;

public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private String clubName;
    private String isCurrentPlaying;
    private String playerName;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size,
                              String clubName, String isCurrnetPlaying) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.clubName = clubName;
        this.isCurrentPlaying = isCurrnetPlaying;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size,
                              String playerName) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.playerName = playerName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setIsCurrentPlaying(String isCurrentPlaying) {
        this.isCurrentPlaying = isCurrentPlaying;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public String getClubName() {
        return clubName;
    }

    public String getIsCurrentPlaying() {
        return isCurrentPlaying;
    }

    public long getSize() {
        return size;
    }
}

