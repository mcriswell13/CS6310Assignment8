/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.PriorityQueue
 */
package edu.gatech;

import edu.gatech.Rider;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Stop {
    private Integer ID;
    private String stopName;
    private PriorityQueue<Rider> ridersOnStop;

    public Stop() {
        this.ID = -1;
    }

    public Stop(int uniqueValue) {
        this.ID = uniqueValue;
        this.stopName = "";
        this.ridersOnStop = new PriorityQueue();
    }

    public Stop(int uniqueValue, String inputName, String address) {
        this.ID = uniqueValue;
        this.stopName = inputName;
    }

    public void setName(String inputName) {
        this.stopName = inputName;
    }

    public PriorityQueue<Rider> getRiders() {
        return this.ridersOnStop;
    }

    public Integer getID() {
        return this.ID;
    }

    public String getName() {
        return this.stopName;
    }

    public void displayEvent() {
        System.out.println(" bus stop: " + Integer.toString((int)this.ID));
    }

    public void takeTurn() {
        System.out.println("get new people - exchange with bus when it passes by");
    }

    public void addRiderToStop(Rider rider) {
        this.ridersOnStop.add((Object)rider);
    }

    public Double findDistance(Stop destination) {
        return null;
    }

    public ArrayList<Rider> exchangeRiders(int initialPassengerCount, int capacity) {
        ArrayList riderList = new ArrayList();
        int riderCount = this.ridersOnStop.size();
        int ableToBoard = capacity - initialPassengerCount;
        int i = 0;
        while (i < riderCount) {
            if (riderCount > ableToBoard) {
                riderList.add((Object)((Rider)this.ridersOnStop.poll()));
                System.out.println("Capacity has been reached for this bus. Rider(s) must wait for next bus.");
                break;
            }
            ++i;
        }
        return riderList;
    }

    public void displayInternalStatus() {
        System.out.print("> stop - ID: " + Integer.toString((int)this.ID));
        System.out.print(" name: " + this.stopName + " waiting: " + Integer.toString((int)this.ridersOnStop.size()));
    }

    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != this.getClass()) {
            result = false;
        } else {
            Stop me = (Stop)object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }
}
