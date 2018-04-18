package edu.gatech;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TransportationSystem {
    private HashMap<Integer, Stop> stops = new HashMap<Integer, Stop>();
    private HashMap<Integer, Route> routes = new HashMap<Integer, Route>();
    private HashMap<Integer, Bus> buses = new HashMap<Integer, Bus>();
    private HashMap<Integer, Rail> rails;

    public Stop getStop(int stopID) {
        if (this.stops.containsKey(stopID)) {
            return this.stops.get(stopID);
        }
        return null;
    }

    public Rail getRail(int railID) {
        if (this.rails.containsKey(railID)) {
            return this.rails.get(railID);
        }
        return null;
    }

    public Route getRoute(int routeID) {
        if (this.routes.containsKey(routeID)) {
            return this.routes.get(routeID);
        }
        return null;
    }

    public Bus getBus(int busID) {
        if (this.buses.containsKey(busID)) {
            return this.buses.get(busID);
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
        this.rails.put(uniqueID, rail);
        return uniqueID;
    }

    public int makeStop(int uniqueID, String inputName, String address) {
        this.stops.put(uniqueID, new Stop(uniqueID, inputName, address));
        return uniqueID;
    }

    public int makeRoute(int uniqueID, int inputNumber, String inputName, String routeType) {
        this.routes.put(uniqueID, new Route(uniqueID, inputNumber, inputName, routeType));
        return uniqueID;
    }

    public int makeBus(int uniqueID, int inputRoute, int inputLocation, int inputCapacity, int inputSpeed) {
        this.buses.put(uniqueID, new Bus(uniqueID, inputRoute, inputLocation, inputCapacity, inputSpeed));
        return uniqueID;
    }

    public void appendStopToRoute(int routeID, int nextStopID) {
        (this.routes.get(routeID)).addNewStop(nextStopID);
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
    	ArrayList<MiniPair> busNodes, stopNodes;
    	MiniPairComparator compareEngine = new MiniPairComparator();
    	
    	int[] colorScale = new int[] {9, 29, 69, 89, 101};
    	String[] colorName = new String[] {"#000077", "#0000FF", "#000000", "#770000", "#FF0000"};
    	Integer colorSelector, colorCount, colorTotal;
    	
    	try{
            // create new file access path
            String path="./mts_digraph.dot";
            File file = new File(path);

            // create the file if it doesn't exist
            if (!file.exists()) { file.createNewFile();}

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("digraph G\n");
            bw.write("{\n");
    	
            busNodes = new ArrayList<MiniPair>();
            for (Bus b: buses.values()) { busNodes.add(new MiniPair(b.getID(), b.getRiders().size())); }
            Collections.sort(busNodes, compareEngine);

            colorSelector = 0;
            colorCount = 0;
            colorTotal = busNodes.size();
            for (MiniPair c: busNodes) {
            	if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { colorSelector++; }
            	bw.write("  bus" + c.getID() + " [ label=\"bus#" + c.getID() + " | " + c.getValue() + " riding\", color=\"" + colorName[colorSelector] + "\"];\n");
            }
            bw.newLine();
            
            stopNodes = new ArrayList<MiniPair>();
            for (Stop s: stops.values()) { stopNodes.add(new MiniPair(s.getID(), s.getRiders().size())); }
            Collections.sort(stopNodes, compareEngine);

            colorSelector = 0;
            colorCount = 0;
            colorTotal = stopNodes.size();    	
            for (MiniPair t: stopNodes) {
            	if (((int) (colorCount++ * 100.0 / colorTotal)) > colorScale[colorSelector]) { colorSelector++; }
            	bw.write("  stop" + t.getID() + " [ label=\"stop#" + t.getID() + " | " + t.getValue() + " waiting\", color=\"" + colorName[colorSelector] + "\"];\n");
            }
            bw.newLine();
            
            for (Bus m: buses.values()) {
            	Integer prevStop = routes.get(m.getRouteID()).getStopID(m.getPastLocation());
            	Integer nextStop = routes.get(m.getRouteID()).getStopID(m.getLocation());
            	bw.write("  stop" + Integer.toString(prevStop) + " -> bus" + Integer.toString(m.getID()) + " [ label=\" dep\" ];\n");
            	bw.write("  bus" + Integer.toString(m.getID()) + " -> stop" + Integer.toString(nextStop) + " [ label=\" arr\" ];\n");
            }
    	
            bw.write("}\n");
            bw.close();
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    }
}
