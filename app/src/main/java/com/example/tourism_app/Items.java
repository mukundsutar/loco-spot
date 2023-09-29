package com.example.tourism_app;

import java.io.Serializable;

public class Items implements Serializable {
   String cityname,plcae1,description1,place2,description2,place3,description3,place4,description4,imageurl;

    public Items() {
    }

    public Items(String cityname, String plcae1, String description1, String place2, String description2, String place3, String description3, String place4, String description4, String imageurl) {
        this.cityname = cityname;
        this.plcae1 = plcae1;
        this.description1 = description1;
        this.place2 = place2;
        this.description2 = description2;
        this.place3 = place3;
        this.description3 = description3;
        this.place4 = place4;
        this.description4 = description4;
        this.imageurl = imageurl;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getPlcae1() {
        return plcae1;
    }

    public void setPlcae1(String plcae1) {
        this.plcae1 = plcae1;
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

    public String getPlace4() {
        return place4;
    }

    public void setPlace4(String place4) {
        this.place4 = place4;
    }

    public String getDescription4() {
        return description4;
    }

    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
