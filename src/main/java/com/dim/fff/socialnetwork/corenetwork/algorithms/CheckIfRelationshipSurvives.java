package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Network;

import java.util.stream.Collectors;

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
                .getEdgeSet()
                .stream()
                .filter(relationship -> !getNetwork().exists(relationship))
                .collect(Collectors.toSet())
                .forEach(relationship -> {
                    if (getNetwork().getRelationshipStrength(relationship) < getValue()) {
                        getGraph().removeEdge(relationship);
                    } else {
                        getNetwork().setExists(relationship, true);
                    }
                });


    }
}
