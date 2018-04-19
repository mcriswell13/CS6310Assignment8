
package edu.gatech;

import edu.gatech.Road;
import java.util.ArrayList;
import java.util.HashMap;

public class Route {
    private Integer ID;
    private Integer routeNumber;
    private String routeName;
    private HashMap<Integer, Integer> stopsOnRoute;
    private ArrayList<Road> roadsBetweenStops;
    private String routeType;

    public Route() {
        this.ID = -1;
    }

    public Route(int uniqueValue) {
        this.ID = uniqueValue;
        this.routeNumber = -1;
        this.routeName = "";
        this.stopsOnRoute = new HashMap<Integer,Integer>();
    }

    public Route(int uniqueValue, int inputNumber, String inputName) {
        this.ID = uniqueValue;
        this.routeNumber = inputNumber;
        this.routeName = inputName;
        this.stopsOnRoute = new HashMap<Integer, Integer>();
        this.roadsBetweenStops = new ArrayList<Road>();
    }

    public Route(int uniqueValue, int inputNumber, String inputName, String routeType) {
        this.ID = uniqueValue;
        this.routeNumber = inputNumber;
        this.routeName = inputName;
        this.stopsOnRoute = new HashMap<Integer,Integer>();
        this.roadsBetweenStops = new ArrayList<Road>();
        this.routeType = routeType;
    }

    public void setNumber(int inputNumber) {
        this.routeNumber = inputNumber;
    }

    public void setName(String inputName) {
        this.routeName = inputName;
    }

    public void addNewStop(int stopID) {
        this.stopsOnRoute.put(this.stopsOnRoute.size(), stopID);
    }

    public void addNewRoadBetweenStop(Road road) {
        this.roadsBetweenStops.add(road);
    }

    public Integer getID() {
        return this.ID;
    }

    public Integer getNumber() {
        return this.routeNumber;
    }

    public String getName() {
        return this.routeName;
    }

    public void displayEvent() {
        System.out.println(" bus route: " + Integer.toString((int)this.ID));
    }

    public void takeTurn() {
        System.out.println("provide next stop on route along with the distance");
    }

    public Integer getNextLocation(int routeLocation) {
        int routeSize = this.stopsOnRoute.size();
        if (routeSize > 0) {
            return (routeLocation + 1) % routeSize;
        }
        return -1;
    }

    public Integer getStopID(int routeLocation) {
        return (Integer)this.stopsOnRoute.get((Object)routeLocation);
    }

    public Integer getLength() {
        return this.stopsOnRoute.size();
    }

    public void displayInternalStatus() {
        System.out.print("> route - ID: " + Integer.toString((int)this.ID));
        System.out.print(" number: " + Integer.toString((int)this.routeNumber) + " name: " + this.routeName);
        System.out.print(" stops: [ ");
        int i = 0;
        while (i < this.stopsOnRoute.size()) {
            System.out.print(String.valueOf((Object)Integer.toString((int)i)) + ":" + Integer.toString((int)((Integer)this.stopsOnRoute.get((Object)i))) + " ");
            ++i;
        }
        System.out.println("]");
    }

    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != this.getClass()) {
            result = false;
        } else {
            Route me = (Route)object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

    public ArrayList<Road> getRoadsBetweenStops() {
        return this.roadsBetweenStops;
    }

    public void setRoadsBetweenStops(ArrayList<Road> roadBetweenStops) {
        this.roadsBetweenStops = roadBetweenStops;
    }

    public String getRouteType() {
        return this.routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }
}
