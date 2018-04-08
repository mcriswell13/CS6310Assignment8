/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.util.Comparator
 */
package edu.gatech;

import edu.gatech.MiniPair;
import java.util.Comparator;

public class MiniPairComparator
implements Comparator<MiniPair> {
    public int compare(MiniPair x, MiniPair y) {
        return x.getValue() - y.getValue();
    }
}
