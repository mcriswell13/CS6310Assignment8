/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.util.Comparator
 */
package edu.gatech;

import edu.gatech.SimEvent;
import java.util.Comparator;

public class SimEventComparator
implements Comparator<SimEvent> {
    public int compare(SimEvent x, SimEvent y) {
        if (y == null) {
            return -1;
        }
        if (x == null) {
            return 1;
        }
        return x.getRank() - y.getRank();
    }
}
