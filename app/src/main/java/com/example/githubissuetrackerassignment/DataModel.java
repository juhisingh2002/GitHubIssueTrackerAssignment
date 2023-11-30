package com.example.githubissuetrackerassignment;

// DataModel.java
public class DataModel {
    private String title;
    private String createdDate;
    private String closedDate;
    private String userName;
    private String userImage;

    public DataModel(String title, String createdDate, String closedDate, String userName, String userImage) {
        this.title = title;
        this.createdDate = createdDate;
        this.closedDate = closedDate;
        this.userName = userName;
        this.userImage = userImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
