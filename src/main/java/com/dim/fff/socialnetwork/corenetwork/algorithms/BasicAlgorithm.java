package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;
import org.graphstream.algorithm.Algorithm;
import org.graphstream.graph.Graph;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 09.12.17
 */
public abstract class BasicAlgorithm implements Algorithm {

    private final Network network;

    public Network getNetwork() {
        return network;
    }

    public BasicAlgorithm(Network network) {
        this.network = network;
    }

    @Override
    public void init(Graph graph) {
        // do nothing
    }


}
