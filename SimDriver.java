/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Exception
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.sql.Connection
 *  java.sql.DriverManager
 *  java.sql.ResultSet
 *  java.sql.Statement
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Properties
 *  java.util.Random
 *  java.util.Set
 */
package edu.gatech;

import edu.gatech.Route;
import edu.gatech.SimQueue;
import edu.gatech.TransportationSystem;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

public class SimDriver {
    private static SimQueue simEngine;
    private static TransportationSystem martaModel;
    private static Random randGenerator;

    public SimDriver() {
        simEngine = new SimQueue();
        martaModel = new TransportationSystem();
        randGenerator = new Random();
    }

    /*
     * Exception decompiling
     */
    public void runInterpreter() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:394)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:191)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:136)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:95)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:369)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:770)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:702)
        // org.benf.cfr.reader.Main.doClass(Main.java:46)
        // org.benf.cfr.reader.Main.main(Main.java:191)
        throw new IllegalStateException("Decompilation failed");
    }

    private static void uploadMARTAData() {
        HashMap routeLists = new HashMap();
        ArrayList circularRouteList = new ArrayList();
        try {
            Integer routeID;
            ArrayList targetList;
            Integer stopID;
            System.out.println(" connecting to the database");
            String url = "jdbc:postgresql://localhost:5432/martadb";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "cs6310");
            props.setProperty("ssl", "true");
            Connection conn = DriverManager.getConnection((String)url, (Properties)props);
            Statement stmt = conn.createStatement();
            System.out.print(" extracting and adding the stops: ");
            int recordCounter = 0;
            ResultSet rs = stmt.executeQuery("SELECT * FROM apcdata_stops");
            while (rs.next()) {
                stopID = rs.getInt("min_stop_id");
                String stopName = rs.getString("stop_name");
                String address = rs.getString("address");
                martaModel.makeStop(stopID, stopName, address);
                ++recordCounter;
            }
            System.out.println(String.valueOf((Object)Integer.toString((int)recordCounter)) + " added");
            System.out.print(" extracting and adding the routes: ");
            recordCounter = 0;
            rs = stmt.executeQuery("SELECT * FROM apcdata_routes");
            while (rs.next()) {
                routeID = rs.getInt("route");
                String routeName = rs.getString("route_name");
                martaModel.makeRoute(routeID, routeID, routeName, "Bus");
                ++recordCounter;
                routeLists.putIfAbsent((Object)routeID, (Object)new ArrayList());
            }
            System.out.println(String.valueOf((Object)Integer.toString((int)recordCounter)) + " added");
            System.out.print(" extracting and assigning stops to the routes: ");
            recordCounter = 0;
            rs = stmt.executeQuery("SELECT * FROM apcdata_routelist_oneway");
            while (rs.next()) {
                routeID = rs.getInt("route");
                stopID = rs.getInt("min_stop_id");
                targetList = (ArrayList)routeLists.get((Object)routeID);
                if (targetList.contains((Object)stopID)) continue;
                martaModel.appendStopToRoute(routeID, stopID);
                ++recordCounter;
                targetList.add((Object)stopID);
            }
            for (Integer reverseRouteID : routeLists.keySet()) {
                if (circularRouteList.contains((Object)reverseRouteID)) continue;
                targetList = (ArrayList)routeLists.get((Object)reverseRouteID);
                int i = targetList.size() - 1;
                while (i > 0) {
                    martaModel.appendStopToRoute(reverseRouteID, (Integer)targetList.get(i));
                    --i;
                }
            }
            System.out.println(String.valueOf((Object)Integer.toString((int)recordCounter)) + " assigned");
            System.out.print(" extracting and adding the buses and events: ");
            recordCounter = 0;
            int busID = 0;
            rs = stmt.executeQuery("SELECT * FROM apcdata_bus_distributions");
            while (rs.next()) {
                routeID = rs.getInt("route");
                int minBuses = rs.getInt("min_buses");
                int avgBuses = rs.getInt("avg_buses");
                int maxBuses = rs.getInt("max_buses");
                int routeLength = martaModel.getRoute(routeID).getLength();
                int suggestedBuses = SimDriver.randomBiasedValue(minBuses, avgBuses, maxBuses);
                int busesOnRoute = Math.max((int)1, (int)Math.min((int)(routeLength / 2), (int)suggestedBuses));
                boolean startingPosition = false;
                int skip = Math.max((int)1, (int)(routeLength / busesOnRoute));
                int i = 0;
                while (i < busesOnRoute) {
                    simEngine.addNewEvent(0, "move_bus", busID++);
                    ++recordCounter;
                    ++i;
                }
            }
            System.out.println(String.valueOf((Object)Integer.toString((int)recordCounter)) + " added");
            System.out.print(" extracting and adding the rider frequency timeslots: ");
            recordCounter = 0;
            rs = stmt.executeQuery("SELECT * FROM apcdata_rider_distributions");
            while (rs.next()) {
                ++recordCounter;
            }
            System.out.println(String.valueOf((Object)Integer.toString((int)recordCounter)) + " added");
        }
        catch (Exception e) {
            System.err.println("Discovered exception: ");
            System.err.println(e.getMessage());
        }
    }

    private static int randomBiasedValue(int lower, int middle, int upper) {
        int lowerRange = randGenerator.nextInt(middle - lower + 1) + lower;
        int upperRange = randGenerator.nextInt(upper - middle + 1) + middle;
        return (lowerRange + upperRange) / 2;
    }
}
