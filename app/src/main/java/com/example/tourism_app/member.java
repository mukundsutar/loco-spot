package com.example.tourism_app;

public class member {

    String title,id,timestamp,videoUrl;

    public member() {
    }

    public member(String title, String id, String timestamp, String videoUrl) {
        this.title = title;
        this.id = id;
        this.timestamp = timestamp;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
