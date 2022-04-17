package com.eknord.schedule.data;

public class Leg {
    private String startCity = null;
    private String endCity = null;
    private int distance = 0;
 
    public Leg() {
        
    }

    public Leg(String startCity, String endCity, int distance) {
        super();
        this.startCity = startCity;
        this.endCity = endCity;
        this.distance = distance;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}
