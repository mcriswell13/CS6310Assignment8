/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 */
package edu.gatech;

public class MiniPair {
    private Integer pairID;
    private Integer pairValue;

    public MiniPair(int inputID, int inputValue) {
        this.pairID = inputID;
        this.pairValue = inputValue;
    }

    public MiniPair() {
        this.pairID = 0;
        this.pairValue = 0;
    }

    public Integer getID() {
        return this.pairID;
    }

    public Integer getValue() {
        return this.pairValue;
    }
}
