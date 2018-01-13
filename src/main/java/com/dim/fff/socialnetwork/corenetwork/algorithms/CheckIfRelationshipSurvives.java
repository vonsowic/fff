package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
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
                .forEachRemaining(edge -> {
                    if(!edge.getAttribute(Attributes.EXISTS, Boolean.class)) {
                        Integer probability = edge.getAttribute(Attributes.RELATIONSHIP_STRENGTH, Integer.class);
                        if (probability < getValue()) {
                            getGraph().removeEdge(edge);
                        } else {
                            edge.setAttribute(Attributes.EXISTS, true);
                        }
                    }
                });
    }
}
