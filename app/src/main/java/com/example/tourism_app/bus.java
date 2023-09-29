package com.example.tourism_app;

public class bus {

    String Destination, Busno, seats, amount;

    public bus() {
    }

    public bus(String destination, String busno, String seats, String amount) {
        Destination = destination;
        Busno = busno;
        this.seats = seats;
        this.amount = amount;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getBusno() {
        return Busno;
    }

    public void setBusno(String busno) {
        Busno = busno;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
