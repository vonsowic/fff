package com.dim.fff.socialnetwork.corenetwork;

import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import org.graphstream.graph.Graph;

import java.util.Iterator;

/**
 * Social network graph.
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class Network implements Iterable<Network>, Cloneable{

    private final DataLoader dataLoader;
    private final Graph network;

    protected Network(Graph network, DataLoader loader) {
        this.network = network;
        this.dataLoader = loader;
    }

    public Graph getGraph(){
        return this.network;
    }

    @Override
    protected Object clone() {
        return new Network(getGraph(), dataLoader);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<Network> iterator() {
        return new NetworkIterator(this);
    }

    public Network nextGeneration(){
        return this;
    }
}
