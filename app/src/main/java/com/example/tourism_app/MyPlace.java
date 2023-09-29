package com.example.tourism_app;

public class MyPlace {

    String name,place1,description1,place2,description2,place3,description3,lati,longi,imageurl;

    public MyPlace() {
    }

    public MyPlace(String name, String place1, String description1, String place2, String description2, String place3, String description3, String lati, String longi, String imageurl) {
        this.name = name;
        this.place1 = place1;
        this.description1 = description1;
        this.place2 = place2;
        this.description2 = description2;
        this.place3 = place3;
        this.description3 = description3;
        this.lati = lati;
        this.longi = longi;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getPlace3() {
        return place3;
    }

    public void setPlace3(String place3) {
        this.place3 = place3;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
