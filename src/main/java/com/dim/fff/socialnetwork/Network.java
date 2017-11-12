package com.dim.fff.socialnetwork;

import java.util.Iterator;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 11.11.17
 */
public class Network implements Iterable<Network> {
    @Override
    public Iterator<Network> iterator() {
        return new NetworkIterator();
    }
}
