package com.dim.fff.socialnetwork.corenetwork;

import java.util.Iterator;

/**
 * Social network graph.
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public abstract class Network implements Iterable<Network>, Cloneable {

    protected Network(com.google.common.graph.Network<User, Relationship> network) {
        this.network = network;
    }

    protected Network(NetworkFactory factory){
        this(factory.buildNetwork());
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
}
