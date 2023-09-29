package com.example.tourism_app;

public class storehotel {
public String lati,longi,username;
public storehotel(){}

    public storehotel(String lati, String longi, String username) {
        this.lati = lati;
        this.longi = longi;
        this.username = username;
    }

    public String getLati() {
        return lati;
    }

    public String getLongi() {
        return longi;
    }

    public String getUsername() {
        return username;
    }
}
