package com.example.tourism_app;

public class Userreview {

    String Username,Type,Feedback,imageurl;

    public Userreview() {
    }

    public Userreview(String username, String type, String feedback, String imageurl) {
        Username = username;
        Type = type;
        Feedback = feedback;
        this.imageurl = imageurl;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
