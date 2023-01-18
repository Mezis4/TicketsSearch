package ru.netology.data;

public class Tickets implements Comparable<Tickets> {
    protected int id;
    protected int price;
    protected String departureAirport;
    protected String arrivalAirport;
    protected int travelTimeMinute;

    public Tickets(int id, int price, String departureAirport, String arrivalAirport, int travelTimeMinute) {
        this.id = id;
        this.price = price;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.travelTimeMinute = travelTimeMinute;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setTravelTimeMinute(int travelTimeMinute) {
        this.travelTimeMinute = travelTimeMinute;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public int getTravelTimeMinute() {
        return travelTimeMinute;
    }

    @Override
    public int compareTo(Tickets o) {
        if (this.price < o.price) {
            return -1;
        } else if (this.price > o.price) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean matches(String searchFrom, String searchTo) {
        if (getDepartureAirport().contains(searchFrom) && getArrivalAirport().contains(searchTo)) {
            return true;
        } else {
            return false;
        }
    }
}