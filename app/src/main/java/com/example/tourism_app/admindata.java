package com.example.tourism_app;

public class admindata {
    String name,monumber,email;

    public admindata() {
    }

    public admindata(String name, String monumber, String email) {
        this.name = name;
        this.monumber = monumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonumber() {
        return monumber;
    }

    public void setMonumber(String monumber) {
        this.monumber = monumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
