/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 */
package edu.gatech;

public class Road {
    private Integer maximumSpeedAllowed;
    private Integer trafficVolume;
    private Double length;
    private Integer currentStopID;
    private Integer destinationStopID;

    public Road(int maximumSpeedAllowed, int trafficVolume, Double length, int currentStopID, int destinationStopID) {
        this.maximumSpeedAllowed = maximumSpeedAllowed;
        this.trafficVolume = trafficVolume;
        this.length = length;
        this.currentStopID = currentStopID;
        this.destinationStopID = destinationStopID;
    }

    public Integer getMaximumSpeedAllowed() {
        return this.maximumSpeedAllowed;
    }

    public void setMaximumSpeedAllowed(Integer maximumSpeedAllowed) {
        this.maximumSpeedAllowed = maximumSpeedAllowed;
    }

    public Integer getTrafficVolume() {
        return this.trafficVolume;
    }

    public void setTrafficVolume(Integer trafficVolume) {
        this.trafficVolume = trafficVolume;
    }

    public Double getLength() {
        return this.length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getCurrentStopID() {
        return this.currentStopID;
    }

    public void setCurrentStopID(Integer currentStopID) {
        this.currentStopID = currentStopID;
    }

    public Integer getDestinationStopID() {
        return this.destinationStopID;
    }

    public void setDestinationStopID(Integer destinationStopID) {
        this.destinationStopID = destinationStopID;
    }
}
