package edu.gatech;

public class Rider {
    private Integer currentStopID;
    private Integer destinationStopID;

    public Rider(int currentStopID, int destinationStopID) {
        this.currentStopID = currentStopID;
        this.destinationStopID = destinationStopID;
    }

    public int getDestinationStop() {
        return this.destinationStopID;
    }

    public void setDestinationStop(int stopID) {
        this.destinationStopID = stopID;
    }

    public int getCurrentStop() {
        return this.currentStopID;
    }

    public void setCurrentStop(int stopID) {
        this.currentStopID = stopID;
    }
}
