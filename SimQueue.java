package edu.gatech;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SimQueue {
    private static PriorityQueue<SimEvent> eventQueue;
    private Comparator<SimEvent> simComparator;
    final static Integer passengerFrequency = 3;

    public SimQueue() {
        simComparator = new SimEventComparator();
        eventQueue = new PriorityQueue<SimEvent>(100, simComparator);
    }

    public void triggerNextEvent(TransportationSystem busModel) throws Exception {
        if (eventQueue.size() > 0) {
            SimEvent activeEvent = eventQueue.poll();
            activeEvent.displayEvent();
            switch (activeEvent.getType()) {
                case "move_bus":
                    // identify the bus that will move
                    Bus activeBus = busModel.getBus(activeEvent.getID());
                    System.out.println(" the bus being observed is: " + Integer.toString(activeBus.getID()));

                    // identify the current stop
                    Route activeRoute = busModel.getRoute(activeBus.getRouteID());
                    System.out.println(" the bus is driving on route: " + Integer.toString(activeRoute.getID()));

                    int activeLocation = activeBus.getLocation();
                    int activeStopID = activeRoute.getStopID(activeLocation);
                    Stop activeStop = busModel.getStop(activeStopID);
                    System.out.println(" the bus is currently at stop: " + Integer.toString(activeStop.getID()) + " - " + activeStop.getName());

                    // drop off and pickup new passengers at current stop
                    int currentPassengers = activeBus.getRiders().size();
                    
                    System.out.println(" there are " + currentPassengers + " on the bus");
                    System.out.println(" there are " + activeStop.getRiders().size() + " at the current stop");

                    ArrayList<Rider> riderList = activeStop.exchangeRiders(currentPassengers, activeBus.getCapacity());
                    System.out.println(" passengers pre-stop: " + Integer.toString(currentPassengers));
                    //Remove riders from bus. They have reached their destination.
                    ArrayList<Rider> busRiders = new ArrayList<Rider>();
                    for(Rider rider : activeBus.getRiders()) {
                    	if(rider.getDestinationBusStop() == activeStopID) {
                    		busRiders.add(rider);
                    	}
                    }
                    for(Rider rider : busRiders) {
                    	System.out.println("Rider " + rider.getID() + " arrived at their destination stop.");
                    	activeBus.getRiders().remove(rider);
                    }
                    System.out.println("Rider list size: " + riderList.size());
                    for(Rider rider : riderList) 
                    {
                    	activeBus.addRiderToVehicle(rider);
                    }  
                    
                    System.out.println(" there are " + activeBus.getRiders().size() + " on the bus after pickup");
                    
                    // determine next stop
                    int nextLocation = activeRoute.getNextLocation(activeLocation);
                    System.out.println(" next location ID: " + nextLocation);
                    int nextStopID = activeRoute.getStopID(nextLocation);
                    double hours = 0.0;
                    double distance = 0.0;
                    int roadCount = 0;
                    for (Road road : activeRoute.getRoadsBetweenStops()) {
                    	if(road.getDestinationStopID() == nextStopID && road.getCurrentStopID() == activeStopID) {
                    		roadCount++;
                    		hours = hours + ((double)(road.getTrafficVolume()/100.0)*(road.getLength()/(double)activeBus.getSpeed()));
                    		distance = distance + road.getLength();
                    	}
                    }
                    if(roadCount == 0) {
                    	throw new Exception("There should be at least one road in between these 2 stops.");
                    }
                    System.out.println(" distance traveled: " + distance);
                    System.out.println(" time traveled: " + hours + " hours");
                    Stop nextStop = busModel.getStop(nextStopID);
                    System.out.println(" the bus is heading to stop: " + Integer.toString(nextStopID) + " - " + nextStop.getName() + "\n");

                    // find distance to stop to determine next event time
                    // conversion is used to translate time calculation from hours to minutes
                    activeBus.setLocation(nextLocation);

                    // generate next event for this bus
                    eventQueue.add(new SimEvent(activeEvent.getRank() + 1, "move_bus", activeEvent.getID()));
                    break;
                case "move_rail":
                    // identify the bus that will move
                    Rail activeRail = busModel.getRail(activeEvent.getID());
                    System.out.println(" the rail being observed is: " + Integer.toString(activeRail.getID()));

                    // identify the current stop
                    Route activeRailRoute = busModel.getRoute(activeRail.getRouteID());
                    System.out.println(" the bus is driving on route: " + Integer.toString(activeRailRoute.getID()));

                    int activeRailLocation = activeRail.getLocation();
                    int activeRailStopID = activeRailRoute.getStopID(activeRailLocation);
                    Stop activeRailStop = busModel.getStop(activeRailStopID);
                    System.out.println(" the bus is currently at stop: " + Integer.toString(activeRailStop.getID()) + " - " + activeRailStop.getName());

                    // drop off and pickup new passengers at current stop
                    int currentRailPassengers = activeRail.getRiders().size();
                    //pick up riders from stop and then add to vehicle
                    ArrayList<Rider> railRiderList = activeRailStop.exchangeRiders(currentRailPassengers, activeRail.getCapacity());
                    System.out.println(" passengers pre-stop: " + Integer.toString(currentRailPassengers) + " post-stop: " + (currentRailPassengers + railRiderList.size()));
                    //Remove riders from bus. They have reached their destination.
                    for(Rider rider : activeRail.getRiders()) {
                    	if(rider.getDestinationBusStop() == activeRailStopID) {
                    		activeRail.removeRiderFromBus(rider);
                    	}
                    }
                    
                    for(Rider rider : railRiderList) {
                    	activeRail.addRiderToVehicle(rider);
                    }

                    // determine next stop
                    int nextRailLocation = activeRailRoute.getNextLocation(activeRailLocation);
                    int nextRailStopID = activeRailRoute.getStopID(nextRailLocation);
                    Stop nextRailStop = busModel.getStop(nextRailStopID);
                    System.out.println(" the bus is heading to stop: " + Integer.toString(nextRailStopID) + " - " + nextRailStop.getName() + "\n");

                    // find distance to stop to determine next event time
                    //*****Need to determine how to calculate distance using roads
                    Double travelRailDistance = activeRailStop.findDistance(nextRailStop);
                    // conversion is used to translate time calculation from hours to minutes
                    int travelRailTime = 1 + (travelRailDistance.intValue() * 60 / activeRail.getSpeed());
                    activeRail.setLocation(nextRailLocation);

                    // generate next event for this bus
                    eventQueue.add(new SimEvent(activeEvent.getRank() + travelRailTime, "move_rail", activeEvent.getID()));
                    break;
                default:
                    System.out.println(" event not recognized");
                    break;
            }
        } else {
            System.out.println(" event queue empty");
        }
    }

    public void addNewEvent(Integer eventRank, String eventType, Integer eventID) {
        eventQueue.add(new SimEvent(eventRank, eventType, eventID));
    }
}
