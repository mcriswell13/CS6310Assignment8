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

    public void triggerNextEvent(TransportationSystem transModel) throws Exception {
        if (eventQueue.size() > 0) {
            SimEvent activeEvent = eventQueue.poll();
            activeEvent.displayEvent();
            switch (activeEvent.getType()) {
                case "move_vehicle":
                    // identify the bus that will move
                    TransportationVehicle activeVehicle = transModel.getVehicle(activeEvent.getID());
                    System.out.println(" the vehicle being observed is: " + Integer.toString(activeVehicle.getID()));

                    // identify the current stop
                    Route activeRoute = transModel.getRoute(activeVehicle.getRouteID());
                    if(activeRoute.getRouteType() == "RAIL") {
                    	System.out.println("THIS IS A RAIL");
                    }
                    else if(activeRoute.getRouteType() == "BUS") {
                    	System.out.println("THIS IS A BUS");
                    }
                    System.out.println(" the vehicle is driving on route: " + Integer.toString(activeRoute.getID()));

                    int activeLocation = activeVehicle.getLocation();
                    int activeStopID = activeRoute.getStopID(activeLocation);
                    Stop activeStop = transModel.getStop(activeStopID);
                    System.out.println(" the vehicle is currently at stop: " + Integer.toString(activeStop.getID()) + " - " + activeStop.getName());

                    // drop off and pickup new passengers at current stop
                                      
                    System.out.println(" there are " + activeVehicle.getRiders().size() + " on the vehicle before exchange");
                    System.out.println(" there are " + activeStop.getRiders().size() + " at the current stop");

                    ArrayList<Rider> riders = new ArrayList<Rider>();
                    for(Rider rider : activeVehicle.getRiders()) {
                    	if(rider.getDestinationStop() == activeStopID) {
                    		riders.add(rider);
                    	}
                    }
                    for(Rider rider : riders) {
                    	System.out.println("Rider arrived at their destination stop.");
                    	activeVehicle.getRiders().remove(rider);
                    }
                    
                    int currentPassengers = activeVehicle.getRiders().size();
                    ArrayList<Rider> ridersForIncorrectBus = new ArrayList<Rider>();
                    while(activeStop.getRiders().size() > 0 && activeVehicle.getCapacity() != activeVehicle.getRiders().size()) {
                    	ArrayList<Rider> riderList = activeStop.exchangeRiders(currentPassengers, activeVehicle.getCapacity());
                        
                        for(Rider rider : riderList) 
                        {
                        	if(activeRoute.getStopID(rider.getDestinationStop()) != null) {
                        		activeVehicle.addRiderToVehicle(rider);
                        	}
                        	else {
                        		System.out.println("Rider added to temp array list");
                        		ridersForIncorrectBus.add(rider);
                        	}
                        } 
                    }
                    for(Rider rider : ridersForIncorrectBus) {
                    	activeStop.addRiderToStop(0, rider);
                    }
                     
                    
                    System.out.println(" there are " + activeVehicle.getRiders().size() + " on the vehicle after pickup");
                    
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
                    		if(activeRoute.getRouteType() == "BUS") {
                    			double speedUsed = Math.max(activeVehicle.getSpeed(), road.getMaximumSpeedAllowed());
                    			hours = hours + (((1.0)/(double)(road.getTrafficVolume()/100.0))*(road.getLength()/speedUsed))*(road.getCurvature());//bad traffic should increase time
                    		}
                    		else if (activeRoute.getRouteType() == "RAIL") {
                    			hours = hours + (road.getLength()/(double)activeVehicle.getSpeed())*(road.getCurvature());
                    		}
                    		distance = distance + road.getLength()*(road.getCurvature());
                    	}
                    }
                    System.out.println(" distance traveled: " + distance + " miles");
                    System.out.println(" time traveled: " + hours + " hours");
                    if(roadCount == 0) {
                    	throw new Exception("There should be at least one road in between these 2 stops.");
                    }
                    Stop nextStop = transModel.getStop(nextStopID);
                    System.out.println(" the vehicle is heading to stop: " + Integer.toString(nextStopID) + " - " + nextStop.getName() + "\n");

                    // find distance to stop to determine next event time
                    // conversion is used to translate time calculation from hours to minutes
                    activeVehicle.setLocation(nextLocation);

                    // generate next event for this vehicle
                    eventQueue.add(new SimEvent(activeEvent.getRank() + 1, "move_vehicle", activeEvent.getID()));
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
