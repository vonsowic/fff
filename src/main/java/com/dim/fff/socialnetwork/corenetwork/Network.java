package com.dim.fff.socialnetwork.corenetwork;

import com.dim.fff.socialnetwork.dataprovider.DataLoader;
import org.graphstream.graph.Graph;

import java.util.Collection;
import java.util.Iterator;

/**
 * Social network graph.
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public abstract class Network implements Iterable<Network>, Cloneable, DataLoader<Long> {

    private Graph network;

    private Collection<Relationship> relationships;

    private Collection<? extends User<Long>> users;

    protected Network(Graph network, DataLoader loader) {
        this.network = network;
        relationships = loader.getAllRelationships();
        users = loader.getAllUsers();
    }

    public Graph getGraph(){
        return this.network;
    }


    @Override
    public Collection<? extends User<Long>> getAllUsers() {
        return users;
    }

    @Override
    public Collection<Relationship> getAllRelationships() {
        return relationships;
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
