/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Comparator
 *  java.util.PriorityQueue
 */
package edu.gatech;

import edu.gatech.SimEvent;
import edu.gatech.SimEventComparator;
import edu.gatech.TransportationSystem;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SimQueue {
    private static PriorityQueue<SimEvent> eventQueue;
    private Comparator<SimEvent> simComparator = new SimEventComparator();
    static final Integer passengerFrequency;

    static {
        passengerFrequency = 3;
    }

    public SimQueue() {
        eventQueue = new PriorityQueue(100, this.simComparator);
    }

    /*
     * Exception decompiling
     */
    public void triggerNextEvent(TransportationSystem transportationModel) {
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

    public void addNewEvent(Integer eventRank, String eventType, Integer eventID) {
        eventQueue.add((Object)new SimEvent(eventRank, eventType, eventID));
    }
}
