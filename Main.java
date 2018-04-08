/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package edu.gatech;

import edu.gatech.SimDriver;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Mass Transit Simulation System Starting...");
        SimDriver commandInterpreter = new SimDriver();
        commandInterpreter.runInterpreter();
    }
}
