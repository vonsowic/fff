package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.01.18
 */
public class RemoveTooOldRelationships extends BasicAlgorithm {
    public RemoveTooOldRelationships(Network network) {
        super(network);
        setValue(10);
    }

    @Override
    public void compute() {
        getGraph()
                .getEdgeIterator()
                .forEachRemaining(relationship -> {
                    if(getNetwork().exists(relationship)){
                        if(getNetwork().getRelationshipStrength(relationship) - getNetwork().getRelationshipAge(relationship) < getValue()){
                            getGraph().removeEdge(relationship);
                        }
                    }
                });
    }
}
