package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;


/**
 * Create every possible relationship.
 * Mark each of them with attribute exists: false if this edge represents potential relationship
 *
 * Add 0 probability to each of edges.
 * If relationship exists, then it means it is probability of loosing connection,
 * otherwise it is probability of creating const connection
 *
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 25.11.17
 */
public class SetZeroProbabilities extends BasicAlgorithm{

    public SetZeroProbabilities(Network network) {
        super(network);
    }


    @Override
    public void compute() {
        getNetwork()
                .getGraph()
                .getEdgeIterator()
                .forEachRemaining(relationship -> getNetwork().setStrength(relationship, 0));
    }

}
