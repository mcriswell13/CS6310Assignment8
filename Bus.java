/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Integer
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 */
package edu.gatech;

import edu.gatech.Rider;
import edu.gatech.TransportationVehicle;
import java.io.PrintStream;
import java.util.ArrayList;

public class Bus
extends TransportationVehicle {
    public Bus(int uniqueValue, int inputRoute, int inputLocation, int inputCapacity, int inputSpeed) {
        super(uniqueValue, inputRoute, inputLocation, inputCapacity, inputSpeed);
    }

    public void displayEvent() {
        System.out.println(" bus: " + Integer.toString((int)this.getID()));
    }

    public void displayInternalStatus() {
        System.out.print("> bus - ID: " + Integer.toString((int)this.getID()) + " route: " + Integer.toString((int)this.getRouteID()));
        System.out.print(" location from: " + Integer.toString((int)this.getPastLocation()) + " to: " + Integer.toString((int)this.getLocation()));
        System.out.print(" passengers: " + Integer.toString((int)this.getRiders().size()) + " capacity: " + Integer.toString((int)this.getCapacity()));
        System.out.println(" speed: " + Integer.toString((int)this.getSpeed()));
    }
}
