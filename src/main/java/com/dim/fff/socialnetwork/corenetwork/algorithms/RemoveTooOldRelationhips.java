package com.dim.fff.socialnetwork.corenetwork.algorithms;

import com.dim.fff.socialnetwork.corenetwork.Attributes;
import com.dim.fff.socialnetwork.corenetwork.Network;

/**
 * @author Michał Wąsowicz
 * @version 1.0
 * @since 12.01.18
 */
public class RemoveTooOldRelationhips extends BasicAlgorithm {
    public RemoveTooOldRelationhips(Network network) {
        super(network);
        setValue(10);
    }

    @Override
    public void compute() {
        getGraph()
                .getEdgeIterator()
                .forEachRemaining(relationship -> {
                    if(relationship.getAttribute(Attributes.EXISTS)){
                        if(relationship.getAttribute(Attributes.CREATED_AT, Integer.class) < getValue()){
                            getGraph().removeEdge(relationship);
                        }
                    }
                });
    }
}
