/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.FileWriter
 *  java.io.PrintStream
 *  java.io.Writer
 *  java.lang.Double
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.Collection
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.HashMap
 *  java.util.List
 *  java.util.PriorityQueue
 */
package edu.gatech;

import edu.gatech.Bus;
import edu.gatech.MiniPair;
import edu.gatech.MiniPairComparator;
import edu.gatech.Rail;
import edu.gatech.Rider;
import edu.gatech.Road;
import edu.gatech.Route;
import edu.gatech.Stop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class TransportationSystem {
    private HashMap<Integer, Stop> stops = new HashMap();
    private HashMap<Integer, Route> routes = new HashMap();
    private HashMap<Integer, Bus> buses = new HashMap();
    private HashMap<Integer, Rail> rails;

    public Stop getStop(int stopID) {
        if (this.stops.containsKey((Object)stopID)) {
            return (Stop)this.stops.get((Object)stopID);
        }
        return null;
    }

    public Rail getRail(int railID) {
        if (this.rails.containsKey((Object)railID)) {
            return (Rail)this.rails.get((Object)railID);
        }
        return null;
    }

    public Route getRoute(int routeID) {
        if (this.routes.containsKey((Object)routeID)) {
            return (Route)this.routes.get((Object)routeID);
        }
        return null;
    }

    public Bus getBus(int busID) {
        if (this.buses.containsKey((Object)busID)) {
            return (Bus)this.buses.get((Object)busID);
        }
        return null;
    }

    public int makeRoadBetweenStop(int uniqueID, int maximumSpeedAllowed, int trafficVolume, Double length, int currentStopID, int destinationStopID) {
        Road road = new Road(maximumSpeedAllowed, trafficVolume, length, currentStopID, destinationStopID);
        Route route = this.getRoute(uniqueID);
        route.addNewRoadBetweenStop(road);
        return uniqueID;
    }

    public int makeRider(int uniqueID, int currentBusStopID, int destinationBusStopID) {
        Rider rider = new Rider(uniqueID, currentBusStopID, destinationBusStopID);
        Stop busStop = this.getStop(currentBusStopID);
        busStop.addRiderToStop(rider);
        System.out.println(" Rider: " + uniqueID + " is on stop: " + busStop.getID().toString());
        return uniqueID;
    }

    public int makeRail(int uniqueID, int inputRoute, int inputLocation, int inputPassengers, int inputCapacity, int inputSpeed, String axisDirection) {
        Rail rail = new Rail(uniqueID, inputRoute, inputLocation, inputCapacity, inputSpeed, axisDirection);
        this.rails.put((Object)uniqueID, (Object)rail);
        return uniqueID;
    }

    public int makeStop(int uniqueID, String inputName, String address) {
        this.stops.put((Object)uniqueID, (Object)new Stop(uniqueID, inputName, address));
        return uniqueID;
    }

    public int makeRoute(int uniqueID, int inputNumber, String inputName, String routeType) {
        this.routes.put((Object)uniqueID, (Object)new Route(uniqueID, inputNumber, inputName, routeType));
        return uniqueID;
    }

    public int makeBus(int uniqueID, int inputRoute, int inputLocation, int inputCapacity, int inputSpeed) {
        this.buses.put((Object)uniqueID, (Object)new Bus(uniqueID, inputRoute, inputLocation, inputCapacity, inputSpeed));
        return uniqueID;
    }

    public void appendStopToRoute(int routeID, int nextStopID) {
        ((Route)this.routes.get((Object)routeID)).addNewStop(nextStopID);
    }

    public HashMap<Integer, Stop> getStops() {
        return this.stops;
    }

    public HashMap<Integer, Route> getRoutes() {
        return this.routes;
    }

    public HashMap<Integer, Bus> getBuses() {
        return this.buses;
    }

    public HashMap<Integer, Rail> getRails() {
        return this.rails;
    }

    public void displayModel() {
        MiniPairComparator compareEngine = new MiniPairComparator();
        int[] colorScale = new int[]{9, 29, 69, 89, 101};
        String[] colorName = new String[]{"#000077", "#0000FF", "#000000", "#770000", "#FF0000"};
        try {
            String path = "./mts_digraph.dot";
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter((Writer)fw);
            bw.write("digraph G\n");
            bw.write("{\n");
            ArrayList busNodes = new ArrayList();
            Collections.sort((List)busNodes, (Comparator)compareEngine);
            Integer colorSelector = 0;
            Integer colorCount = 0;
            Integer colorTotal = busNodes.size();
            for (MiniPair c : busNodes) {
                Integer n = colorCount;
                colorCount = n + 1;
                if ((int)((double)n.intValue() * 100.0 / (double)colorTotal.intValue()) > colorScale[colorSelector]) {
                    colorSelector = colorSelector + 1;
                }
                bw.write("  bus" + (Object)c.getID() + " [ label=\"bus#" + (Object)c.getID() + " | " + (Object)c.getValue() + " riding\", color=\"" + colorName[colorSelector] + "\"];\n");
            }
            bw.newLine();
            ArrayList stopNodes = new ArrayList();
            for (Stop s : this.stops.values()) {
                stopNodes.add((Object)new MiniPair(s.getID(), s.getRiders().size()));
            }
            Collections.sort((List)stopNodes, (Comparator)compareEngine);
            colorSelector = 0;
            colorCount = 0;
            colorTotal = stopNodes.size();
            for (MiniPair t : stopNodes) {
                Integer n = colorCount;
                colorCount = n + 1;
                if ((int)((double)n.intValue() * 100.0 / (double)colorTotal.intValue()) > colorScale[colorSelector]) {
                    colorSelector = colorSelector + 1;
                }
                bw.write("  stop" + (Object)t.getID() + " [ label=\"stop#" + (Object)t.getID() + " | " + (Object)t.getValue() + " waiting\", color=\"" + colorName[colorSelector] + "\"];\n");
            }
            bw.newLine();
            for (Bus m : this.buses.values()) {
                Integer prevStop = ((Route)this.routes.get((Object)m.getRouteID())).getStopID(m.getPastLocation());
                Integer nextStop = ((Route)this.routes.get((Object)m.getRouteID())).getStopID(m.getLocation());
                bw.write("  stop" + Integer.toString((int)prevStop) + " -> bus" + Integer.toString((int)m.getID()) + " [ label=\" dep\" ];\n");
                bw.write("  bus" + Integer.toString((int)m.getID()) + " -> stop" + Integer.toString((int)nextStop) + " [ label=\" arr\" ];\n");
            }
            bw.write("}\n");
            bw.close();
        }
        catch (Exception e) {
            System.out.println((Object)e);
        }
    }
}
