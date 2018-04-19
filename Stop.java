package edu.gatech;

import edu.gatech.Rider;
import java.util.ArrayList;
import java.util.Comparator;
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
        this.ridersOnStop = new PriorityQueue<Rider>();
    }

    public Stop(int uniqueValue, String inputName) {
    	Comparator<Rider> comparator = new RiderComparator();
    	this.ridersOnStop = new PriorityQueue<Rider>(100, comparator);
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
        this.ridersOnStop.add(rider);
    }

    public Double findDistance(Stop destination) {
        return null;
    }

    public ArrayList<Rider> exchangeRiders(int initialPassengerCount, int capacity) {
        ArrayList<Rider> riderList = new ArrayList<Rider>();
        int riderCount = this.ridersOnStop.size();
        System.out.println("exchangeRiders, riders on this stop size: " + riderCount);
        int ableToBoard = capacity - initialPassengerCount;
        System.out.println("exchangeRiders, able to board bus: " + ableToBoard);
        int i = 0;
        while (i < riderCount) {
           Rider polledRider = this.ridersOnStop.poll();
           System.out.println(polledRider.getID());
           riderList.add(polledRider);
           if (riderCount > ableToBoard) {
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
 // StringLengthComparator.java

    public class RiderComparator implements Comparator<Rider>
    {
        @Override
        public int compare(Rider x, Rider y)
        {
            // Assume neither string is null. Real code should
            // probably be more robust
            // You could also just return x.length() - y.length(),
            // which would be more efficient.
            if (x.getID() < y.getID())
            {
                return -1;
            }
            if (x.getID() > y.getID())
            {
                return 1;
            }
            return 0;
        }
    }
    
}