/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package edu.gatech;

import java.io.PrintStream;

public class SimEvent {
    private Integer timeRank;
    private String eventType;
    private Integer eventID;

    public SimEvent() {
        this.timeRank = 0;
    }

    public SimEvent(int inputRank, String inputType, int inputID) {
        this.timeRank = inputRank;
        this.eventType = inputType;
        this.eventID = inputID;
    }

    public void setRank(int inputRank) {
        this.timeRank = inputRank;
    }

    public void setType(String inputType) {
        this.eventType = inputType;
    }

    public void setID(int inputID) {
        this.eventID = inputID;
    }

    public Integer getRank() {
        return this.timeRank;
    }

    public String getType() {
        return this.eventType;
    }

    public Integer getID() {
        return this.eventID;
    }

    public void displayEvent() {
        System.out.println("# event rank: " + Integer.toString((int)this.timeRank) + " type: " + this.eventType + " ID: " + Integer.toString((int)this.eventID));
    }

    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != this.getClass()) {
            result = false;
        } else {
            SimEvent me = (SimEvent)object;
            if (this.timeRank == me.getRank() && this.eventType == me.getType() && this.eventID == me.getID()) {
                result = true;
            }
        }
        return result;
    }
}
