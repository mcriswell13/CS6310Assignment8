package edu.gatech;

import edu.gatech.Rider;
import java.io.PrintStream;
import java.util.ArrayList;

public class TransportationVehicle {
    private Integer ID;
    private Integer route;
    private Integer nextLocation;
    private Integer prevLocation;
    private ArrayList<Rider> riders;
    private Integer capacity;
    private Integer speed;

    public TransportationVehicle() {
        this.ID = -1;
    }

    public TransportationVehicle(int uniqueValue) {
        this.ID = uniqueValue;
        this.route = -1;
        this.nextLocation = -1;
        this.prevLocation = -1;
        this.capacity = -1;
        this.speed = -1;
    }

    public TransportationVehicle(int uniqueValue, int inputRoute, int inputLocation, int inputCapacity, int inputSpeed) {
        this.ID = uniqueValue;
        this.route = inputRoute;
        this.nextLocation = inputLocation;
        this.prevLocation = inputLocation;
        this.capacity = inputCapacity;
        this.speed = inputSpeed;
    }

    public void setRoute(int inputRoute) {
        this.route = inputRoute;
    }

    public void setLocation(int inputLocation) {
        this.prevLocation = this.nextLocation;
        this.nextLocation = inputLocation;
    }

    public void addRiderToBus(Rider busRider) {
        this.riders.add((Object)busRider);
    }

    public void removeRiderFromBus(Rider busRider) {
        this.riders.remove((Object)busRider);
    }

    public void setCapacity(int inputCapacity) {
        this.capacity = inputCapacity;
    }

    public void setSpeed(int inputSpeed) {
        this.speed = inputSpeed;
    }

    public Integer getRouteID() {
        return this.route;
    }

    public Integer getLocation() {
        return this.nextLocation;
    }

    public Integer getPastLocation() {
        return this.prevLocation;
    }

    public Integer getID() {
        return this.ID;
    }

    public ArrayList<Rider> getRiders() {
        return this.riders;
    }

    public void setID(Integer iD) {
        this.ID = iD;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void takeTurn() {
        System.out.println("drop off passengers - pickup passengers to capacity - move to next stop");
    }
}

