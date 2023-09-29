package com.example.tourism_app;

public class food {

    String cityname,food1,food2,food3,food4;

    public food() {
    }



    public food(String cityname, String food1, String food2, String food3, String food4) {
        this.cityname = cityname;
        this.food1 = food1;
        this.food2 = food2;
        this.food3 = food3;
        this.food4 = food4;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getFood1() {
        return food1;
    }

    public void setFood1(String food1) {
        this.food1 = food1;
    }

    public String getFood2() {
        return food2;
    }

    public void setFood2(String food2) {
        this.food2 = food2;
    }

    public String getFood3() {
        return food3;
    }

    public void setFood3(String food3) {
        this.food3 = food3;
    }

    public String getFood4() {
        return food4;
    }

    public void setFood4(String food4) {
        this.food4 = food4;
    }
}
