package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 05.01.18
 */
public class CheckIfRelationshipSurvives extends BasicAlgorithm{

    public CheckIfRelationshipSurvives(Network network) {
        super(network);
        setValue(15);
    }

    @Override
    public void compute() {
        getGraph()
                .getEdgeIterator()
                .forEachRemaining(relationship -> {
                    if(!getNetwork().exists(relationship)) {
                        Integer probability = getNetwork().getRelationshipStrength(relationship);
                        if (probability < getValue()) {
                            getGraph().removeEdge(relationship);
                        } else {
                            getNetwork().setExists(relationship, true);
                        }
                    }
                });
    }
}
