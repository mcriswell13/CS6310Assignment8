package edu.gatech;

public class Road {
    private Integer maximumSpeedAllowed;
    private Integer trafficVolume;//1 (bad) - 100 (good) range
    private Double length;
    private Integer currentStopID;
    private Integer destinationStopID;
    private Double curvature;//1.0 (no curve) - 2.0 (very curvy) range

    public Road(int maximumSpeedAllowed, int trafficVolume, Double length, int currentStopID, int destinationStopID, Double curvature) {
        this.maximumSpeedAllowed = maximumSpeedAllowed;
        this.trafficVolume = trafficVolume;
        this.length = length;
        this.currentStopID = currentStopID;
        this.destinationStopID = destinationStopID;
        this.curvature = curvature;
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

	public Double getCurvature() {
		return curvature;
	}

	public void setCurvature(Double curvature) {
		this.curvature = curvature;
	}
}
