package com.dim.fff.socialnetwork.corenetwork;

import org.graphstream.graph.Graph;

import java.util.Iterator;

/**
 * Social network graph.
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public abstract class Network implements Iterable<Network>, Cloneable {

    protected Network(Graph network) {
        this.network = network;
    }

    private Graph network;

    public Graph getNetwork(){
        return this.network;
    }

    @Override
    protected abstract Object clone();

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<Network> iterator() {
        return new NetworkIterator(this);
    }

    public abstract Network nextGeneration();

}
