package edu.gatech;

public class Rider {
    private Integer ID;
    private Integer currentBusStopID;
    private Integer destinationBusStopID;

    public Rider(int uniqueValue, int currentBusStopID, int destinationBusStopID) {
        this.ID = uniqueValue;
        this.currentBusStopID = currentBusStopID;
        this.destinationBusStopID = destinationBusStopID;
    }

    public Integer getID() {
        return this.ID;
    }

    public int getDestinationBusStop() {
        return this.destinationBusStopID;
    }

    public void setDestinationBusStop(int busStopID) {
        this.destinationBusStopID = busStopID;
    }

    public int getCurrentBusStop() {
        return this.currentBusStopID;
    }

    public void setCurrentBusStop(int busStopID) {
        this.currentBusStopID = busStopID;
    }
}
