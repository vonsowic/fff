package com.dim.fff.socialnetwork.corenetwork;

import com.dim.fff.socialnetwork.dataprovider.DataLoader;

import java.util.Iterator;
import java.util.Set;

/**
 * Social network graph.
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public abstract class Network implements Iterable<Network>, Cloneable, DataLoader {

    protected Network(com.google.common.graph.Network<User, Relationship> network) {
        this.network = network;
    }

    private com.google.common.graph.Network<User, Relationship> network;

    protected com.google.common.graph.Network<User, Relationship> getNetwork(){
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

    @Override
    public Set<? extends User> getAllUsers() {
        return network.nodes();
    }

    @Override
    public Set<Relationship> getAllRelationships() {
        return network.edges();
    }
}
