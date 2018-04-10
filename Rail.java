package edu.gatech;

import edu.gatech.TransportationVehicle;

public class Rail
extends TransportationVehicle {
    private String axisDirection;

    public Rail(int uniqueValue, int inputRoute, int inputLocation, int inputCapacity, int inputSpeed, String axisDirection) {
        super(uniqueValue, inputRoute, inputLocation, inputCapacity, inputSpeed);
        this.axisDirection = axisDirection;
    }

    public void displayEvent() {
        System.out.println(" rail: " + Integer.toString((int)this.getID()));
    }

    public void displayInternalStatus() {
        System.out.print("> rail - ID: " + Integer.toString((int)this.getID()) + " route: " + Integer.toString((int)this.getRouteID()));
        System.out.print(" location from: " + Integer.toString((int)this.getPastLocation()) + " to: " + Integer.toString((int)this.getLocation()));
        System.out.print(" passengers: " + Integer.toString((int)this.getRiders().size()) + " capacity: " + Integer.toString((int)this.getCapacity()));
        System.out.println(" speed: " + Integer.toString((int)this.getSpeed()));
    }

    public String getDirectionalType() {
        return this.axisDirection;
    }

    public void setDirectionalType(String directionalType) {
        this.axisDirection = directionalType;
    }
}

