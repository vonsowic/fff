package com.dim.fff.socialnetwork.corenetwork;

import java.util.Iterator;

/**
 * Iterate over network generations.
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.11.17
 */
public class NetworkIterator implements Iterator<Network> {

    private Network network;

    NetworkIterator(Network initialState){
        this.network = initialState;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Network next() {
        Network previous = network;
        network = network.nextGeneration();
        return previous;
    }
}
